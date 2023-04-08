package com.example.demo.controller;

import com.example.demo.dto.Todo;
import com.example.demo.dao.TodoRepository;
import com.example.demo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class TodoController {

    private static TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        Todo todaytodo = new Todo((long) 1, "hanging leg raise", 0, false);
        service.save(todaytodo);
    }

    @GetMapping("/todos")
    public List<Todo> listTodo() {
        return service.findAll();
    }

    @PostMapping("/todos")
    public HashMap newTodo(@RequestBody Todo newTodo) {
        HashMap hashmap = new HashMap<>();
        hashmap.put("id", service.save(newTodo).getId());
        return hashmap;
    }
}