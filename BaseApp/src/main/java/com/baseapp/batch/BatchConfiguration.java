package com.baseapp.batch;

import com.baseapp.batch.domain.ConvertedMessage;
import com.baseapp.batch.domain.TranslatedText;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.builder.HibernateCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    SimpleJobLauncher launcher(JobRepository repository) {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(repository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return jobLauncher;
    }

    @Bean
    HibernateCursorItemReader<TranslatedText> reader(SessionFactory sessionFactory) {
        HibernateCursorItemReaderBuilder<TranslatedText> builder = new HibernateCursorItemReaderBuilder<>();
        return builder
                .name("reader")
                .queryString(
                        "SELECT NEW com.baseapp.batch.domain.TranslatedText(TO.id , T.sentence ,TO.sentence , TO.language) " +
                                "FROM Task T, TranslateOutput TO " +
                                "WHERE TO.task=T.id AND TO.isSavedInFile=false " +
                                "ORDER BY T.id ")
                .sessionFactory(sessionFactory)
                .useStatelessSession(true)
                .build();
    }

    @Bean
    Step saveTranslations(LanguageClassifier classifier, TextProcessor processor,
                          HibernateCursorItemReader<TranslatedText> reader) {
        return stepBuilderFactory.get("saveTranslations")
                .<TranslatedText, ConvertedMessage>chunk(50)
                .reader(reader)
                .processor(processor)
                .writer(classifier)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    Job save(Step step) {
        return jobBuilderFactory.get("save")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

}
