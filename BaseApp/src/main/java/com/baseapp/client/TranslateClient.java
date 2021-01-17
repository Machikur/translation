package com.baseapp.client;

import com.baseapp.domain.Language;
import com.baseapp.domain.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class TranslateClient {

    private final RestTemplate restTemplate;

    public String getTranslate(TaskDto taskDto) {
        URI translateUri = getTranslateUri(taskDto.getLanguageFrom(), taskDto.getLanguageTo(), taskDto.getSentence());
        return restTemplate.getForObject(translateUri, String.class);
    }

    public URI getTranslateUri(Language languageFrom, Language languageTo, String sentence) {
        return UriComponentsBuilder.fromHttpUrl("http://localhost:8080/translate")
                .queryParam("languageFrom", languageFrom)
                .queryParam("languageTo", languageTo)
                .queryParam("sentence", sentence)
                .build().encode().toUri();
    }

}
