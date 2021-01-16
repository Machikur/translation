package com.translator.controller;

import com.translator.domain.TranslateQuery;
import com.translator.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {

    private final WordService wordService;

    @PostMapping("/translate")
    public String getTranslate(@RequestBody TranslateQuery translateQuery) {
        return wordService.getTranslation(translateQuery);
    }

}
