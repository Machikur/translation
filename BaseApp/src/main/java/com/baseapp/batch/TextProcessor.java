package com.baseapp.batch;

import com.baseapp.batch.domain.ConvertedMessage;
import com.baseapp.batch.domain.TranslatedText;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TextProcessor implements ItemProcessor<TranslatedText, ConvertedMessage> {
    @Override
    public ConvertedMessage process(TranslatedText item) {
        String message = String.format("Before Translate : \"%s\", after translate: \"%s\", Language - %s"
                , item.getBefore(), item.getAfter(), item.getLanguage());
        return new ConvertedMessage(message, item.getLanguage());
    }
}
