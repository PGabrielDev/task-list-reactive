package com.pggm.TaskListReactive.tasks.api;

import com.pggm.TaskListReactive.tasks.dtos.CreateTaskDTO;
import com.pggm.TaskListReactive.tasks.models.Task;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping(value = "tasks")
public interface ApiTask {
    @PostMapping
    public Mono<Task> createTask(@RequestBody final CreateTaskDTO createTaskDTO);

    @PutMapping(value = "/{taskId}")
    public Mono<Task> completedTask(@PathVariable final String taskId);

    @GetMapping(value = "/{taskId}")
    public Mono<Task> getTask(@PathVariable final String taskId);

}
