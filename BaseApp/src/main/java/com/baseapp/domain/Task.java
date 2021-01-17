package com.baseapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Language languageFrom;

    @Enumerated(EnumType.STRING)
    private Language languageTo;

    private String sentence;

    private boolean isTranslated = false;


    public Task(Language languageFrom, Language languageTo, String sentence) {
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
        this.sentence = sentence;
    }

    public void setTranslated(boolean translated) {
        isTranslated = translated;
    }
}
