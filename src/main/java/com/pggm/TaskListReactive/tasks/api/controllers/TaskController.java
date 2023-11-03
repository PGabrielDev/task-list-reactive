package com.pggm.TaskListReactive.tasks.api.controllers;

import com.pggm.TaskListReactive.tasks.api.ApiTask;
import com.pggm.TaskListReactive.tasks.dtos.CreateTaskDTO;
import com.pggm.TaskListReactive.tasks.models.Task;
import com.pggm.TaskListReactive.tasks.models.TaskCompleted;
import com.pggm.TaskListReactive.tasks.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class TaskController implements ApiTask {

    private final TaskRepository taskRepository;

    @Override
    public Mono<Task> createTask(CreateTaskDTO createTaskDTO) {
        log.info("Controlador de criacao de task");
        final var taskID = UUID.randomUUID().toString();
        final Task task = Task
                .builder()
                .id(taskID)
                .title(createTaskDTO.title())
                .description(createTaskDTO.description())
                .completed(TaskCompleted.NOT_COMPLETED)
                .build();
        return taskRepository.createTask(taskID, task);
    }

    @Override
    public Mono<Task> completedTask(String taskId) {
        return taskRepository.completedTask(taskId);
    }

    @Override
    public Mono<Task> getTask(String taskId) {
        return taskRepository.getTask(taskId);
    }
}
