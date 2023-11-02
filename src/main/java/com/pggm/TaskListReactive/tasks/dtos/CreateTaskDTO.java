package com.pggm.TaskListReactive.tasks.dtos;

import com.pggm.TaskListReactive.tasks.models.TaskCompleted;

public record CreateTaskDTO(
        String title, String description
) {
}
