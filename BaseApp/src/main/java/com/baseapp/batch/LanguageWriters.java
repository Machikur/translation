package com.baseapp.batch;

import com.baseapp.batch.domain.ConvertedMessage;
import com.baseapp.domain.Language;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LanguageWriters {

    @Bean(name = "writersMap")
    Map<String, FlatFileItemWriter<ConvertedMessage>> writersMap() {
        Map<String, FlatFileItemWriter<ConvertedMessage>> writersMap = new HashMap<>();
        for (Language l : Language.values()) {
            writersMap.put(l.name(), getWriterForLanguage(l));
        }
        return writersMap;
    }

    private FlatFileItemWriter<ConvertedMessage> getWriterForLanguage(Language language) {
        FlatFileItemWriter<ConvertedMessage> builder = new FlatFileItemWriter<>();
        DelimitedLineAggregator<ConvertedMessage> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter("\n");
        builder.setLineAggregator(aggregator);
        builder.setResource(new FileSystemResource(String.format("translation/%s.txt", language.name())));
        builder.setName(language.name());
        builder.setAppendAllowed(true);
        builder.open(new ExecutionContext());
        return builder;
    }

}
