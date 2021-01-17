package com.baseapp.batch.domain;

import com.baseapp.domain.Language;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConvertedMessage {
    private String message;
    private Language language;

    @Override
    public String toString() {
        return message;
    }

}
