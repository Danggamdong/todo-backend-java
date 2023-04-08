package com.example.demo.dao;

import com.example.demo.dto.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoRepository {
    List<Todo> list = new ArrayList<>();

    public List<Todo> findAll() {
        return list;
    }

    public Todo save(Todo todo) {

        Todo saveTodo = new Todo(
                todo.getId(),
                todo.getDescription(),
                todo.getCreated_at(),
                todo.isIs_finished());
        list.add(saveTodo);
        return saveTodo;
    }

    public int count() {
        return list.size();
    }

    public void deleteAll() {
        list.clear();
    }
}
