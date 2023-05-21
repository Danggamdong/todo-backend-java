package com.example.demo.dao;

import com.example.demo.dto.Todo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface TodoDao {

    public List<Todo> findAll();

    public Todo save(Todo todo);

    public String delete(String id);

    public String update(HashMap<String, Object> updateTodo);

    public void deleteAll();

    public int count();

    public Todo find(String id);
}
