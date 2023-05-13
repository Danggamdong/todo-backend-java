package com.example.demo.dao;

import com.example.demo.dto.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
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
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreatedAt(),
                todo.getFinishedAt(),
                todo.isFinished());

        list.add(saveTodo);
        return saveTodo;
    }

    public String delete(String id) {
        int idx = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                idx = i;
                break;
            }
        }
        if (idx != -1) return list.remove(idx).getId();
        return "";
    }

    public String update(HashMap<String, Object> updateTodo) {
        String id = (String) updateTodo.get("id");
        int idx = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return "";

        if (updateTodo.get("title") != null) {
            Todo todo = list.get(idx);
            todo.setTitle((String) updateTodo.get("title"));
        }
        if (updateTodo.get("description") != null) {
            Todo todo = list.get(idx);
            todo.setDescription((String) updateTodo.get("description"));
        }
        if (updateTodo.get("finishedAt") != null) {
            Todo todo = list.get(idx);
            todo.setFinishedAt((int) updateTodo.get("finishedAt"));
        }
        if (updateTodo.get("isFinished") != null) {
            Todo todo = list.get(idx);
            todo.setFinished((boolean) updateTodo.get("isFinished"));
        }
        return list.get(idx).getId();
    }

    public int count() {
        return list.size();
    }

    public void deleteAll() {
        list.clear();
    }
}
