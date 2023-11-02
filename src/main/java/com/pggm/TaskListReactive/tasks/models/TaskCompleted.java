package com.pggm.TaskListReactive.tasks.models;

public enum TaskCompleted {
    COMPLETED("COMPLETED"),
    NOT_COMPLETED("NOTCOMPLETED");

    private final String status;

    TaskCompleted(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
