package com.pggm.TaskListReactive.tasks.models.repository;

public interface IDatabase {

    <T> T save(final String key, final T value);

    <T> T get(final String key, Class<T> clazz);

}
