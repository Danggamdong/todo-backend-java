package com.example.demo;

import com.example.demo.dto.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void getId() {
        final Todo todo = new Todo();
        String testId = "1";
        todo.setId(testId);
        final String id = todo.getId();

        assertEquals(testId, id);
    }

    @Test
    void getDescription() {
        final Todo todo = new Todo();
        todo.setDescription("romanian deadlift");
        final String description = todo.getDescription();

        assertEquals("romanian deadlift", description);
    }

    @Test
    void getCreatedAt() {
        final Todo todo = new Todo();
        int testCreateedAt = 1;
        todo.setCreatedAt(testCreateedAt);
        final int createdAt = todo.getCreatedAt();

        assertEquals(testCreateedAt, createdAt);
    }

    @Test
    void isIs_finished() {
        final Todo todo = new Todo();
        todo.setFinished(false);
        final boolean isFinished = todo.isFinished();

        assertEquals(false, isFinished);
    }
}
