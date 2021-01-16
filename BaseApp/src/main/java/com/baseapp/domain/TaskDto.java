package com.baseapp.domain;

import lombok.Data;

@Data
public class TaskDto {
    private final Language languageFrom;
    private final Language languageTo;
    private final String sentence;
}
