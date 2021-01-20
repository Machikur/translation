package com.baseapp.batch;

import com.baseapp.batch.domain.ConvertedMessage;
import com.baseapp.batch.domain.TranslatedText;
import com.baseapp.repo.TranslateOutputRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextProcessor implements ItemProcessor<TranslatedText, ConvertedMessage> {

    private final TranslateOutputRepo repo;

    @Override
    public ConvertedMessage process(TranslatedText item) {
        repo.setSavedById(item.getId());
        String message = String.format("Before Translate : \"%s\", after translate: \"%s\", Language - %s"
                , item.getBefore(), item.getAfter(), item.getLanguage());
        return new ConvertedMessage(message, item.getLanguage());
    }

}
