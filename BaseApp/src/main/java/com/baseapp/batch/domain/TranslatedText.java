package com.baseapp.batch.domain;

import com.baseapp.domain.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslatedText {

    private Long id;
    private String after;
    private String before;
    private Language language;
}
