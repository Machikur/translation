package com.baseapp.batch;

import com.baseapp.batch.domain.ConvertedMessage;
import com.baseapp.batch.domain.TranslatedText;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    JdbcCursorItemReader<TranslatedText> reader(DataSource dataSource) {
        JdbcCursorItemReader<TranslatedText> reader = new JdbcCursorItemReader<>();
        BeanPropertyRowMapper<TranslatedText> mapper = new BeanPropertyRowMapper<>();
        mapper.setMappedClass(TranslatedText.class);
        reader.setDataSource(dataSource);
        reader.setSql("SELECT T.ID, T.SENTENCE AS BEFORE,TO.SENTENCE AS AFTER, TO.LANGUAGE " +
                "FROM TASK T " +
                "JOIN TRANSLATE_OUTPUT TO ON TO.TASK_ID=T.ID " +
                "ORDER BY T.ID");
        reader.setRowMapper(mapper);
        return reader;
    }

    @Bean
    Step saveTranslations(LanguageClassifier classifier, TextProcessor processor,
                          JdbcCursorItemReader<TranslatedText> reader) {
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
