package com.pggm.TaskListReactive.tasks.api;

import com.pggm.TaskListReactive.tasks.dtos.CreateTaskDTO;
import com.pggm.TaskListReactive.tasks.models.Task;
import com.pggm.TaskListReactive.tasks.models.TaskCompleted;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@RequestMapping(value = "tasks")
public interface ApiTask {
    @PostMapping
    public Mono<Task> createTask(@RequestBody final CreateTaskDTO createTaskDTO);

}
