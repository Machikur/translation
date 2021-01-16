package com.baseapp.batch;

import com.baseapp.domain.TranslateOutput;
import com.baseapp.repo.TranslateOutputRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TranslationWriter implements ItemWriter<TranslateOutput> {

    private final TranslateOutputRepo repo;

    @Override
    public void write(List<? extends TranslateOutput> items) {
        repo.saveAll(items);
    }
}
