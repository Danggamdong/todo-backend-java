package com.example.demo;

public class TodoNotFoundException extends RuntimeException{
    TodoNotFoundException(Long id) {
        super("Could not find todo " + id);
    }
}
