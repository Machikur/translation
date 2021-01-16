package com.baseapp.client;

import com.baseapp.domain.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TranslateClient {

    private static final String TRANSLATE_URL = "http://localhost:8080/translate";
    private final RestTemplate restTemplate;

    public String getTranslate(TaskDto taskDto) {
        return restTemplate.postForObject(TRANSLATE_URL, taskDto, String.class);
    }

}
