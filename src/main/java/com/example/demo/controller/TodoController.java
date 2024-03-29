package com.example.demo.controller;

import com.example.demo.dto.Todo;
import com.example.demo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    }

    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public void isOk() {
    }

    @GetMapping("/health")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public void isHealth() {
    }

    @GetMapping("/todos")
    public List<Todo> listTodo() {
        return service.findAll();
    }

    @GetMapping("/todos/{id}")
    public Todo findTodo(@PathVariable("id") String id) {
        Todo todo = service.find(id);
        return todo;
    }

    @PostMapping("/todos")
    public HashMap newTodo(@RequestBody Todo newTodo) {
        HashMap hashmap = new HashMap<>();
        hashmap.put("id", service.save(newTodo).getId());
        return hashmap;
    }

    @DeleteMapping("/todos/{id}")
    public HashMap deleteTodo(@PathVariable("id") String id) {
        HashMap hashmap = new HashMap<>();
        hashmap.put("id", service.delete(id));
        return hashmap;
    }

    @PutMapping("/todos/{id}")
    public HashMap updateTodo(@PathVariable("id") String id,
                              @RequestBody HashMap<String, Object> updateTodo) {

        updateTodo.put("id", id);
        HashMap hashmap = new HashMap<>();
        hashmap.put("id", service.update(updateTodo));

        return hashmap;
    }

}
