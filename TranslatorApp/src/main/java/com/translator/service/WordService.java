package com.translator.service;

import com.translator.domain.Language;
import com.translator.domain.Word;
import com.translator.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public String getTranslation(Language languageFrom, Language languageTo, String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String actualWord : words) {
            Optional<Word> meaningId = wordRepository.findByLanguageAndWord(languageFrom, actualWord);
            if (meaningId.isPresent()) {
                Optional<Word> afterTranslate = wordRepository.findWordByMeaningIdAndLanguage(meaningId.get().getMeaningId(), languageTo);
                actualWord = afterTranslate.map(Word::getWord).orElse(actualWord);
            }
            builder.append(actualWord).append(" ");
        }
        return builder.toString().trim();
    }

}
