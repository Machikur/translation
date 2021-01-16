package com.baseapp.event;

import com.baseapp.domain.Language;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TranslateQuery extends ApplicationEvent {

    private final Long taskId;
    private final Language languageFrom;
    private final Language languageTo;
    private final String sentence;


    public TranslateQuery(Object source, Long taskId, Language languageFrom, Language languageTo, String sentence) {
        super(source);
        this.taskId = taskId;
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
        this.sentence = sentence;
    }

}
