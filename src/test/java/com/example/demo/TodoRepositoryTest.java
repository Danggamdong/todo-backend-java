package com.example.demo;

import com.example.demo.dao.TodoRepository;
import com.example.demo.dto.Todo;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;


@SpringBootTest
public class TodoRepositoryTest {
    @Autowired
    TodoRepository todoRepository;

    @Before
    public void cleanup() {
        todoRepository.deleteAll();
    }

    @Test
    @DisplayName("레포지토리 저장 테스트")
    void saveTodo() {
        Todo todo = new Todo("1", "Todo", "hanging leg raise", 0, 0, false);
        todoRepository.save(todo);
        List<Todo> listTodo = todoRepository.findAll();
        Todo savedTodo = listTodo.get(0);
        
        Assertions.assertThat(todo.getId()).isEqualTo(savedTodo.getId());
        Assertions.assertThat(todo.getDescription()).isEqualTo(savedTodo.getDescription());
        Assertions.assertThat(todo.getCreatedAt()).isEqualTo(savedTodo.getCreatedAt());
        Assertions.assertThat(todo.isFinished()).isEqualTo(savedTodo.isFinished());
        Assertions.assertThat(savedTodo.getId()).isNotNull();
        Assertions.assertThat(todoRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("레포지토리 업데이트 테스트")
    void updateTodo() {
        todoRepository.deleteAll();
        Todo updateTodo = new Todo("2", "Todo", "hanging leg raise", 0, 0, false);
        todoRepository.save(updateTodo);
        HashMap<String, Object> hashMap = new HashMap<>();
        String newTitle = "exercise";
        hashMap.put("id", updateTodo.getId());
        hashMap.put("title", newTitle);
        String id = todoRepository.update(hashMap);
        List<Todo> todoList = todoRepository.findAll();
        System.out.println(todoRepository.findAll().size());
        Todo updatedTodo = todoList.get(0);

        Assertions.assertThat(id).isEqualTo(updateTodo.getId());
        Assertions.assertThat(todoRepository.count()).isEqualTo(1);
        Assertions.assertThat(updatedTodo.getTitle()).isEqualTo(newTitle);

    }

    @Test
    @DisplayName("레포지토리 삭제 테스트")
    void deleteTodo() {
        todoRepository.deleteAll();
        Todo deleteTodo = new Todo("3", "crossfit", "power snatch", 0, 0, false);
        todoRepository.save(deleteTodo);
        String id = todoRepository.delete("3");

        Assertions.assertThat(id).isEqualTo(deleteTodo.getId());
        Assertions.assertThat(todoRepository.count()).isEqualTo(0);
    }
}
