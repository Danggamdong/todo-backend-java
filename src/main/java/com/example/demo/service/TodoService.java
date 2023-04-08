package com.example.demo.service;

import com.example.demo.dao.TodoRepository;
import com.example.demo.dto.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TodoService {

    private TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public String delete(String id) {
        return repository.delete(id);
    }

    public String update(HashMap<String, Object> updateTodo) {
        return repository.update(updateTodo);
    }
}
