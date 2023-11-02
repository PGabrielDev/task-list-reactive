package com.pggm.TaskListReactive.tasks.models.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
@Slf4j
public class InMemoryDataBase implements IDatabase {
    private final Map<String, String> DATABASE = new ConcurrentHashMap<>();
    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public <T> T save(String key, T value) {
        final String json = mapper.writeValueAsString(value);
        DATABASE.put(key, json);
        return value;
    }

    @Override
    public <T> Optional<T> get(String key, Class<T> clazz) {
        final var json = DATABASE.get(key);
        return Optional.ofNullable(json).map(j -> {
            try {
                return mapper.readValue(j, clazz);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
