package com.baseapp.batch;

import com.baseapp.domain.TranslateOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    Step saveTranslations(TranslationReader reader, TranslationWriter writer) {
        return stepBuilderFactory.get("saveTranslations")
                .<TranslateOutput, TranslateOutput>chunk(50)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    Job save(Step step) {
        return jobBuilderFactory.get("save")
                .flow(step)
                .end()
                .build();
    }

}
