package com.example.demo;

import com.example.demo.dto.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void getId() {
        final Todo todo = new Todo();
        todo.setId((long) 1);
        final long id = todo.getId();

        assertEquals((long) 1, id);
    }

    @Test
    void getDescription() {
        final Todo todo = new Todo();
        todo.setDescription("romanian deadlift");
        final String description = todo.getDescription();

        assertEquals("romanian deadlift", description);
    }

    @Test
    void getCreated_at() {
        final Todo todo = new Todo();
        todo.setCreated_at(1);
        final int created_at = todo.getCreated_at();

        assertEquals(1, created_at);
    }

    @Test
    void isIs_finished() {
        final Todo todo = new Todo();
        todo.setIs_finished(false);
        final boolean is_finished = todo.isIs_finished();

        assertEquals(false, is_finished);
    }
}
