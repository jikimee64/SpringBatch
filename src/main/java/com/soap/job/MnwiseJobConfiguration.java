package com.soap.job;

import com.soap.application.KakaoAlimTalkApiService;
import com.soap.domain.MzsendlogEntity;
import com.soap.domain.MzsendlogRepository;
import com.soap.domain.MzsendtranEntity;
import com.soap.domain.MzsendtranRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.persistence.EntityManagerFactory;
import java.util.function.Function;

/*
https://jojoldu.tistory.com/493
https://ahndy84.tistory.com/19
 */

@Slf4j
//@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
public class MnwiseJobConfiguration {
    //private MzsendtranRepository mzsendtranRepository;
    private final KakaoAlimTalkApiService kakaoAlimTalkApiService;
    private final MzsendtranRepository mzsendtranRepository;
    private final MzsendlogRepository mzsendlogRepository;

    public static final String JOB_NAME = "multiThreadPagingBatch";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private int chunkSize = 30;

    //@Value("${chunkSize:30}")
//    public void setChunkSize(int chunkSize){
//        this.chunkSize = chunkSize;
//    }

    private int poolSize = 3;

    //@Value("${poolSize:10}")
//    public void setPoolSize(int poolSize){
//        this.poolSize = poolSize;
//    }

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

    @Bean(name = JOB_NAME)
    public Job job(){
        return jobBuilderFactory.get(JOB_NAME)
                .start(step())
                .preventRestart()
                .build();
    }

    @Bean(name = JOB_NAME + "_step")
    @JobScope
    public Step step(){
        log.info("********** This is Step");
        return stepBuilderFactory.get(JOB_NAME +"_step")
                .<MzsendtranEntity, MzsendtranEntity>chunk(chunkSize)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(executor())
                .throttleLimit(poolSize)
                .build();
    }

    @Bean(name = JOB_NAME +"_reader")
    @StepScope
    public JpaPagingItemReader<MzsendtranEntity> reader(){
        log.info("********** This is Reader");
        return new JpaPagingItemReaderBuilder<MzsendtranEntity>()
                .name(JOB_NAME +"_reader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT * FROM MZSENDTRAN WHERE 1=1 LIMIT 30")
                .saveState(false)
                .build();
    }

    private ItemProcessor<MzsendtranEntity, MzsendtranEntity> processor(){
        //API 호출후 가공
        return new ItemProcessor<MzsendtranEntity, MzsendtranEntity>() {
            @Override
            public MzsendtranEntity process(MzsendtranEntity mzsendtranEntity) throws Exception {
                log.info("********** This is ItemProcessor");
                return kakaoAlimTalkApiService.sendAlimTalk(mzsendtranEntity);
            }
        };
    }

    @Bean(name = JOB_NAME +"_writer")
    @StepScope
    public JpaItemWriter<MzsendtranEntity> writer() {
        log.info("********** This is writer");
        //MZSENDTRAN, MZSENDLOG 데이터 저장, MZSENDTRAN 데이터 삭제
        return new JpaItemWriterBuilder<MzsendtranEntity>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
