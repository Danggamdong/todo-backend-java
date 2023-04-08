package com.example.demo.service;

import com.example.demo.dao.TodoRepository;
import com.example.demo.dto.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo save(Todo todo) {
        Todo result = repository.save(todo);
        return result;
    }

    public List<Todo> findAll() {
        List<Todo> result = repository.findAll();
        return result;
    }
}
