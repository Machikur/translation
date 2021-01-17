package com.baseapp.batch.domain;

import com.baseapp.domain.Language;
import lombok.Data;

@Data
public class TranslatedText {
    private Long Id;
    private String after;
    private String before;
    private Language language;
}
