package com.pggm.TaskListReactive.tasks.models.repository;

import java.util.Optional;

public interface IDatabase {
    <T> T save(final String key, final T value);
    <T> Optional<T> get(final String key, Class<T> clazz);

}
