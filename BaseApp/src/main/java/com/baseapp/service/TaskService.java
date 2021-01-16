package com.baseapp.service;

import com.baseapp.domain.Task;
import com.baseapp.domain.TaskDto;
import com.baseapp.event.TranslateQuery;
import com.baseapp.mapper.TaskMapper;
import com.baseapp.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ApplicationEventPublisherAware {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    private ApplicationEventPublisher publisher;
    private LocalDateTime lastCheck;

    public void saveTask(TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        taskRepo.save(task);
    }

    @Scheduled(fixedDelay = 10000L)
    public void checkNewTasksInBase() {
        if (lastCheck == null) {
            lastCheck = LocalDateTime.now();
        }
        List<Task> newTasks = taskRepo.findAllByCreatedTimeAfter(lastCheck);
        lastCheck = LocalDateTime.now();
        List<TranslateQuery> queries = taskMapper.mapToTranslateQueryList(newTasks, this);
        for (TranslateQuery q : queries) {
            publisher.publishEvent(q);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


}
