package com.soap.job;

import com.soap.application.KakaoAlimTalkApiService;
import com.soap.domain.Mzsendlog;
import com.soap.domain.Mzsendtran;
import com.soap.domain.MzsendtranRepository;

import com.soap.job.SampleTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


/**
 * 스프링 배치 설정 클래스
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class MnwiseJobConfiguration {
    private final KakaoAlimTalkApiService kakaoAlimTalkApiService;
    private final MzsendtranRepository mzsendtranRepository;
    public static final String JOB_NAME = "multiThreadPagingBatch";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;

    private int pageSize = 2000;  //mzsendtran에서 한건당 읽어올 단위수
    private int chunkSize = 2000; //mzsendlog에 한건당 커밋할 단위수

    private int poolSize = 3; //스레드 개수

    /**
     * 스레드풀 선언 및 설정
     * 멀티스레드 3개 설정
     */
    @Bean(name = JOB_NAME+"taskPool")
    public TaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);
        executor.setThreadNamePrefix("multi-thread-");
        executor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        executor.initialize();
        return executor;
    }

    /**
     * 작업 단위(Job)설정 : MZSENDTRAN -> MZSENDLOG 테이블 이관 작업
     * step() -> step2() 단계벌 실행
     */
    @Bean(name = JOB_NAME)
    public Job job(){
        return jobBuilderFactory.get(JOB_NAME)
                .start(step()) //MZSENDTRAN SELECT -> API 호출 ->
                .next(step2())
                .preventRestart()
                .build();
    }

    /**
     * JOB내부 프로세스 설정(첫번째 단계) : MZSENDTRAN -> MZSENDLOG 데이터 이관 작업
     * reader        : MZSENDTRAN 데이터 전체건 조회
     * processor     : 카카오 API 호출
     * writer        : MZSENDLOG 데이터 적재
     * taskExecutor  : 스레드풀 설정
     * throttleLimit : 스레드개수 설정
     */
    @Bean(name = JOB_NAME + "_step")
    @JobScope
    public Step step(){
        log.info("********** This is Step");
        return stepBuilderFactory.get(JOB_NAME +"_step")
                .<Mzsendtran, Mzsendlog>chunk(chunkSize)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(executor())
                .throttleLimit(poolSize)
                .build();
    }


    @Bean
    public Tasklet sampleTasklet(MzsendtranRepository mzsendtranRepository) {
        return new SampleTasklet(mzsendtranRepository);
    }

    /**
     * JOB내부 프로세스 설정(두번째 단계) : MZSENDTRAN 데이터 삭제
     * tasklet : MZSENDTRAN 데이터 삭제
     */
    @Bean(name = JOB_NAME + "_step2")
    @JobScope
    public Step step2(){
        log.info("********** This is Step2");
        return stepBuilderFactory.get("testStep")
                .tasklet(sampleTasklet(mzsendtranRepository))
                .build();
    }


    /**
     * STEP1 READER MZSENDTRAN 데이터 전체건 조회
     * JpaPagingItemReader : 멀티스레드 Thread-safe 지원, SQL실행시 자동으로 설정한 pageSize만큼 LIMIT을 붙여줌
     * pageSize            : 한건당 읽어올 데이터 개수 설정
     * queryString         : SQL문
     * saveState(false)    : 실패 발생 후 다음 실행 시 처음부터 다시 실행해주는 설정
     */
    @Bean(name = JOB_NAME +"_reader")
    @StepScope
    public JpaPagingItemReader<Mzsendtran> reader(){
        log.info("********** This is Reader");
        JpaPagingItemReader<Mzsendtran> reader = new JpaPagingItemReader<Mzsendtran>();
        reader = new JpaPagingItemReaderBuilder<Mzsendtran>()
                .name(JOB_NAME +"_reader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(pageSize)
                .queryString("SELECT mz FROM Mzsendtran mz order by mz.sn asc")
                .saveState(false)
                .build();
        return reader;
    }

    /**
     * API 호출 -> Writer()메소드에 MZSENDLOG 엔티티 적재(API 결과값 받아서 추가)
     */
    private ItemProcessor<Mzsendtran, Mzsendlog> processor(){
        return new ItemProcessor<Mzsendtran, Mzsendlog>() {
            @Override
            public Mzsendlog process(Mzsendtran mzsendtran2) throws Exception {
                return kakaoAlimTalkApiService.sendAlimTalk(mzsendtran2);
            }
        };
    }

    /** STEP1 Writer MZSENDLOG 데이터 적재
     * JdbcBatchItemWriter : Bulk Insert 지원
     */
    @Bean
    public JdbcBatchItemWriter<Mzsendlog> writer() {
        log.info("********** This is writer");
        return new JdbcBatchItemWriterBuilder<Mzsendlog>()
                .dataSource(dataSource)
                .sql("insert into mzsendlog(sn, sender_key, channel, snd_type, phone_num, tmpl_cd, subject, snd_msg, sms_snd_msg, sms_snd_num, "
                        + "req_dept_cd, req_usr_id, req_dtm, snd_dtm, rslt_cd, rcpt_msg, rcpt_dtm, sms_snd_dtm, sms_rslt_cd, sms_rcpt_msg,"
                        + "sms_rcpt_dtm, sms_gb, sms_snd_yn, tran_sn, tran_sts, agent_id, slot1, slot2, tr_type_cd, attachment, app_user_id) "
                        + "values (:sn, :senderKey, :channel, :sndType, :phoneNum, :tmplCd, :subject, :sndMsg, :smsSndMsg, :smsSndNum,"
                        + ":reqDeptCd, :reqUsrId, :reqDtm, :sndDtm, :rsltCd, :rcptMsg, :rcptDtm, :smsSndDtm, :smsRsltCd, :smsRcptMsg, "
                        + ":smsRcptDtm, :smsGb, :smsSndYn, :tranSn, :tranSts, :agentId, :slot1, :slot2, :trTypeCd, :attachment, :appUserId)")
                .beanMapped()
                .build();
    }

}
