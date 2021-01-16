package com.baseapp.event;

import com.baseapp.client.TranslateClient;
import com.baseapp.domain.Task;
import com.baseapp.domain.TranslateOutput;
import com.baseapp.mapper.TaskMapper;
import com.baseapp.repo.TaskRepo;
import com.baseapp.repo.TranslateOutputRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslateQueryListener implements ApplicationListener<TranslateQuery> {

    private final TranslateOutputRepo translateOutputRepo;
    private final TaskRepo taskRepo;
    private final TranslateClient translateClient;
    private final TaskMapper taskMapper;

    @Override
    public void onApplicationEvent(TranslateQuery query) {
        String translatedSentence = translateClient.getTranslate(taskMapper.mapToTaskDto(query));
        Optional<Task> task = taskRepo.findById(query.getTaskId());
        TranslateOutput translation = new TranslateOutput(translatedSentence, query.getLanguageTo(), task.get());
        translateOutputRepo.save(translation);
    }

}
