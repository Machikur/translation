package com.translator.domain;

import lombok.Data;

@Data
public class TranslateQuery {
    private String sentence;
    private Language languageFrom;
    private Language languageTo;
}
