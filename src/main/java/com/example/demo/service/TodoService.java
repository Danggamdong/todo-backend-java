package com.example.demo.service;

import com.example.demo.dao.TodoDao;
import com.example.demo.dto.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TodoService {

    private TodoDao dao;

    @Autowired
    public TodoService(TodoDao dao) {
        this.dao = dao;
    }

    public Todo save(Todo todo) {
        return dao.save(todo);
    }

    public List<Todo> findAll() {
        return dao.findAll();
    }

    public String delete(String id) {
        return dao.delete(id);
    }

    public String update(HashMap<String, Object> updateTodo) {
        return dao.update(updateTodo);
    }

    public Todo find(String id) {
        return dao.find(id);
    }
}
