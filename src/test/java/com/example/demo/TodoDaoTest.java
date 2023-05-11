package com.example.demo;

import com.example.demo.dao.TodoDao;
import com.example.demo.dto.Todo;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class TodoDaoTest {
    @Autowired
    TodoDao dao;

    @Before
    public void cleanup() {
        dao.deleteAll();
    }

    @Test
    @DisplayName("sql save test")
    void saveTodo() {
        Todo todo = new Todo("0", "Todo", "hanging leg raise", 0, 0, false);
        dao.save(todo);
        List<Todo> listTodo = dao.findAll();
        Todo savedTodo = listTodo.get(0);

        Assertions.assertThat(todo.getId()).isEqualTo(savedTodo.getId());
        Assertions.assertThat(todo.getDescription()).isEqualTo(savedTodo.getDescription());
        Assertions.assertThat(todo.getCreatedAt()).isEqualTo(savedTodo.getCreatedAt());
        Assertions.assertThat(todo.isFinished()).isEqualTo(savedTodo.isFinished());
        Assertions.assertThat(savedTodo.getId()).isNotNull();
        Assertions.assertThat(dao.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("sql select by id test")
    void findTodo() {
        String id = "2";
        dao.deleteAll();
        Todo todo = new Todo(id, "Todo", "hanging leg raise", 0, 0, false);
        dao.save(todo);
        Todo findTodo = dao.find(id);

        Assertions.assertThat(todo.getId()).isEqualTo(findTodo.getId());
        Assertions.assertThat(todo.getDescription()).isEqualTo(findTodo.getDescription());
        Assertions.assertThat(todo.getCreatedAt()).isEqualTo(findTodo.getCreatedAt());
        Assertions.assertThat(todo.isFinished()).isEqualTo(findTodo.isFinished());
        Assertions.assertThat(findTodo.getId()).isNotNull();
    }

    @Test
    @DisplayName("sql update test")
    void updateTodo() {
        dao.deleteAll();
        Todo updateTodo = new Todo("2", "Todo", "hanging leg raise", 0, 0, false);
        dao.save(updateTodo);
        HashMap<String, Object> hashMap = new HashMap<>();
        String newTitle = "exercise";
        hashMap.put("id", updateTodo.getId());
        hashMap.put("title", newTitle);
        String id = dao.update(hashMap);
        List<Todo> todoList = dao.findAll();
        System.out.println(dao.findAll().size());
        Todo updatedTodo = todoList.get(0);

        Assertions.assertThat(id).isEqualTo(updateTodo.getId());
        Assertions.assertThat(dao.count()).isEqualTo(1);
        Assertions.assertThat(updatedTodo.getTitle()).isEqualTo(newTitle);

    }

    @Test
    @DisplayName("sql delete by id test")
    void deleteTodo() {
        dao.deleteAll();
        Todo deleteTodo = new Todo("3", "crossfit", "power snatch", 0, 0, false);
        dao.save(deleteTodo);
        String id = dao.delete("3");

        Assertions.assertThat(id).isEqualTo(deleteTodo.getId());
        Assertions.assertThat(dao);
        Assertions.assertThat(dao.count()).isEqualTo(0);
    }
}
