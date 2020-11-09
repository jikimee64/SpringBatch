package com.soap.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Spring Batch의 모든 Job은 @Configuration으로 등록해서 사용
 */

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    /*
        simpleJob 이란 이름의 Batch Job을 생성
        job의 이름은 별도로 지정하지 않고, 이렇게 Builder를 통해 지정
     */
    @Bean
    public Job simpleJob(){
        return jobBuilderFactory.get("simpleJob") //
                .start(simpleStep1())
                .build();
    }
    /*
        simpleStep1 이란 이름의 Batch Step을 생성
        jobBuilderFactory.get("simpleJob")와 마찬가지로 Builder를 통해 이름을 지정
        .tasklet((contribution, chunkContext))
        : Step 안에서 수행될 기능들을 명시
        Tasklet은 Step안에서 단일로 수행될 커스텀한 기능들을 선언할때 사용
        여기서는 Batch가 수행되면 log.info(">>>>> This is Step1") 가 출력
     */
    @Bean
    public Step simpleStep1(){
        return stepBuilderFactory.get("simpleStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>> This is Step1");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
