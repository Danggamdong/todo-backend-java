package com.example.demo;

import com.example.demo.dto.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void getId() {
        final Todo todo = new Todo();
        todo.setId("1");
        final String id = todo.getId();

        assertEquals("1", id);
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
        todo.setCreatedAt(1);
        final int createdAt = todo.getCreatedAt();

        assertEquals(1, createdAt);
    }

    @Test
    void isIs_finished() {
        final Todo todo = new Todo();
        todo.setFinished(false);
        final boolean isFinished = todo.isFinished();

        assertEquals(false, isFinished);
    }
}
