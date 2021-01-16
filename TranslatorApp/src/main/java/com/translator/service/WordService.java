package com.translator.service;

import com.translator.domain.Language;
import com.translator.domain.TranslateQuery;
import com.translator.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.translator.domain.Language.ENGLISH;
import static com.translator.domain.Language.POLISH;


@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public String getTranslation(TranslateQuery translateQuery) {
        Language languageFrom = translateQuery.getLanguageFrom();
        Language languageTo = translateQuery.getLanguageTo();
        String[] words = translateQuery.getSentence().split(" ");
        StringBuilder builder = new StringBuilder();
        if (languageFrom.equals(ENGLISH) && languageTo.equals(POLISH)) {
            for (String s : words) {
                Optional<String> word = wordRepository.englishToPolish(s.toLowerCase());
                builder.append(word.orElse(s) + " ");
            }
        } else if (languageFrom.equals(POLISH) && languageTo.equals(ENGLISH)) {
            for (String s : words) {
                Optional<String> word = wordRepository.polishToEnglish(s.toLowerCase());
                builder.append(word.orElse(s) + " ");
            }
        } else {
            throw new IllegalArgumentException("Brak możliwości tłumaczenia");

        }
        return builder.toString();
    }


}
