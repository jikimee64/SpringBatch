package com.soap.job;

import java.util.List;

import com.soap.domain.MzsendtranMapping;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.soap.domain.MzsendtranMapping;
import com.soap.domain.MzsendtranRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SampleTasklet implements Tasklet, StepExecutionListener {

    private List<MzsendtranMapping> snList;
    private final MzsendtranRepository mzsendtranRepository;

    /**
     * execute() 실행 전 비즈니스 로직 설정
     */
    @Override
    public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) {
    }

    /**
     * MZSENDTRAN 데이터 전체 삭제
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        mzsendtranRepository.deleteAllBySnInQuery();
        return RepeatStatus.FINISHED;
    }

    /**
     * execute() 실행 후 비즈니스 로직
     */
    @Override
    public ExitStatus afterStep(org.springframework.batch.core.StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
