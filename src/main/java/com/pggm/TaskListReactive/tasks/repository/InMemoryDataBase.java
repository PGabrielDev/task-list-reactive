package com.pggm.TaskListReactive.tasks.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
public class InMemoryDataBase implements IDatabase {
    private final Map<String, String> DATABASE = new ConcurrentHashMap<>();
    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public <T> T save(String key, T value) {
        final String json = mapper.writeValueAsString(value);
        DATABASE.put(key, json);
        slepp(3_000);
        return value;
    }

    @Override
    public <T> Optional<T> get(String key, Class<T> clazz) throws InterruptedException {
        final var json = DATABASE.get(key);
        slepp(1_000);
        return Optional.ofNullable(json).map(j -> {
            try {
                return mapper.readValue(j, clazz);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void slepp(final long milles) throws InterruptedException {
        Thread.sleep(milles);
    }
}
