package com.pggm.TaskListReactive.tasks.repository;

import com.pggm.TaskListReactive.tasks.models.Task;
import com.pggm.TaskListReactive.tasks.models.TaskCompleted;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class TaskRepository {

    private InMemoryDataBase dataBase;

    public Mono<Task> createTask(final String taskId, final Task task) {
        return Mono.fromCallable(() -> {
            log.info("Saving new Task {}", taskId);
            return dataBase.save(taskId, task);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Task> getTask(final String taskId) {
        return Mono.defer(() -> {
            log.info("Get Task from id {}", taskId);
            final Optional<Task> task;
            try {
                task = dataBase.get(taskId, Task.class);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Mono.justOrEmpty(task);
        }).subscribeOn(Schedulers.parallel());
    }

    public Mono<Task> completedTask(final String taskId) {
        final var task = getTask(taskId)
                .flatMap(taskk -> {
                    log.info("Change completed task {}", taskId);
                    taskk.setCompleted(TaskCompleted.COMPLETED);
                    return Mono.fromCallable(() -> {
                        log.info("Updated Task on Completed {}", taskId);
                        return dataBase.save(taskId, taskk);
                    }).subscribeOn(Schedulers.boundedElastic());
                });
        return task;

    }


}
