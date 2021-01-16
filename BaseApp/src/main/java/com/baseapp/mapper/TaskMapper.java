package com.baseapp.mapper;

import com.baseapp.domain.Task;
import com.baseapp.domain.TaskDto;
import com.baseapp.event.TranslateQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Task mapToTask(TaskDto taskDto) {
        return new Task(taskDto.getLanguageFrom(), taskDto.getLanguageTo(), taskDto.getSentence());
    }

    public List<TranslateQuery> mapToTranslateQueryList(List<Task> tasks, Object source) {
        return tasks.stream()
                .map(task -> new TranslateQuery(source, task.getId(), task.getLanguageFrom(), task.getLanguageTo(), task.getSentence()))
                .collect(Collectors.toList());
    }

    public TaskDto mapToTaskDto(TranslateQuery query) {
        return new TaskDto(query.getLanguageFrom(), query.getLanguageTo(), query.getSentence());
    }

}
