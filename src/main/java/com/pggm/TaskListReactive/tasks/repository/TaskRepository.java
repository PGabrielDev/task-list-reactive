package com.pggm.TaskListReactive.tasks.repository;

import com.pggm.TaskListReactive.tasks.models.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class TaskRepository {

    private InMemoryDataBase dataBase;

    public Mono<Task> createTask(final String taskId, final Task task){
        return Mono.fromCallable(() -> {
            log.info("Saving new Task {}", taskId);
            return dataBase.save(taskId, task);
        });
    }

}
