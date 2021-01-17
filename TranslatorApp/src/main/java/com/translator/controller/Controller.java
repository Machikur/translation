package com.translator.controller;

import com.translator.domain.Language;
import com.translator.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {

    private final WordService wordService;

    @GetMapping("/translate")
    public String getTranslate(@RequestParam Language languageFrom,
                               @RequestParam Language languageTo,
                               @RequestParam String sentence) {
        return wordService.getTranslation(languageFrom, languageTo, sentence);
    }

}
