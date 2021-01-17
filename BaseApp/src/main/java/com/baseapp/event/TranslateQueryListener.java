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

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TranslateQueryListener implements ApplicationListener<TranslateQuery> {

    private final TranslateOutputRepo translateOutputRepo;
    private final TaskRepo taskRepo;
    private final TranslateClient translateClient;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public void onApplicationEvent(TranslateQuery query) {
        String translatedSentence = translateClient.getTranslate(taskMapper.mapToTaskDto(query));
        Task task = taskRepo.findById(query.getTaskId()).get();
        TranslateOutput translation = new TranslateOutput(translatedSentence, query.getLanguageTo(), task);
        translateOutputRepo.save(translation);
        task.setTranslated(true);
        taskRepo.save(task);
    }

}
