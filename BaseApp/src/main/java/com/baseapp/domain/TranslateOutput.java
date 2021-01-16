package com.baseapp.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
public class TranslateOutput {
    @Id
    @GeneratedValue
    private Long id;
    private String sentence;
    private Language language;

    @OneToOne(targetEntity = Task.class, fetch = FetchType.EAGER)
    private Task task;

    public TranslateOutput(String sentence, Language language, Task task) {
        this.sentence = sentence;
        this.language = language;
        this.task = task;
    }
}
