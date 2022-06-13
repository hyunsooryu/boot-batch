package com.hyunsoo.bootbatch.job.validatedParam;


import com.hyunsoo.bootbatch.job.jobListener.JobLoggerListener;
import com.hyunsoo.bootbatch.job.validatedParam.validators.FileParamValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ValidatedParamJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job validatedParamJob(Step validatedParamStep){
        return jobBuilderFactory
                .get("validatedParamJob")
                .incrementer(new RunIdIncrementer())
               // .validator(new FileParamValidator())
                .validator(compositeJobParametersValidator())
                .listener(new JobLoggerListener())
                .start(validatedParamStep)
                .build();
    }


    private CompositeJobParametersValidator compositeJobParametersValidator(){
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        validator.setValidators(List.of(new FileParamValidator()));
        return validator;
    }


    @JobScope
    @Bean
    public Step validatedParamStep(Tasklet validatedParamTasklet) {
        return stepBuilderFactory.get("validatedParamStep")
                .tasklet(validatedParamTasklet)
                .build();
    }

    @StepScope
    @Bean
    public Tasklet validatedParamTasklet(@Value("#{jobParameters['fileName']}") String fileName) {
        return (stepContribution, chunkContext) -> {
            System.out.println(fileName);
            System.out.println("validated Param Tasklet");
            return RepeatStatus.FINISHED;
        };
    }


}
