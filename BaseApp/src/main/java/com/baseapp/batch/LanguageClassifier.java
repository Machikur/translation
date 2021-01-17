package com.baseapp.batch;

import com.baseapp.batch.domain.ConvertedMessage;
import com.baseapp.domain.Language;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.classify.Classifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LanguageClassifier extends ClassifierCompositeItemWriter<ConvertedMessage> {

    public LanguageClassifier(Map<String, FlatFileItemWriter<ConvertedMessage>> writersMap) {
        setClassifier((Classifier<ConvertedMessage, ItemWriter<? super ConvertedMessage>>) classifiable -> {
            for (Language l : Language.values()) {
                if (classifiable.getLanguage().equals(l)) {
                    return writersMap.get(l.name());
                }
            }
            throw new IllegalArgumentException("Brak podanego języka");
        });
    }
}
