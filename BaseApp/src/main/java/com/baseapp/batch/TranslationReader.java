package com.baseapp.batch;

import com.baseapp.domain.TranslateOutput;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@StepScope
public class TranslationReader implements ItemReader<TranslateOutput> {

    private List<TranslateOutput> list;
    private int counter;

    public TranslationReader(@Value("#jobParameters") Map<String, Object> map) {
        Object list = map.get("translationList");
        if (list instanceof List<?>) {
            this.list = (List<TranslateOutput>) list;
            this.counter = 0;
        } else {
            this.list = new ArrayList<>();
            this.counter = 0;
        }
    }

    @Override
    public TranslateOutput read() {
        TranslateOutput translation = null;
        if (counter < list.size()) {
            translation = list.get(counter);
        }
        return translation;
    }

}
