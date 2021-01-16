package com.baseapp.controller;

import com.baseapp.domain.TaskDto;
import com.baseapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TranslateController {

    private final TaskService taskService;

    @PostMapping("translate")
    public void saveTextToTranslate(@RequestBody TaskDto taskDto) {
        taskService.saveTask(taskDto);
    }

}
