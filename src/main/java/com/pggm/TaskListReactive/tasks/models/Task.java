package com.pggm.TaskListReactive.tasks.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Task {
    private String id;
    private String title;
    private String description;
    private TaskCompleted completed;
}
