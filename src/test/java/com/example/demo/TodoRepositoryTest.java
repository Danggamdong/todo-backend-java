package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TodoRepositoryTest {
    @Autowired
    TodoRepository todoRepository;
    @Test
    @DisplayName("레포지토리 저장 테스트")
    void saveTodo(){

        Todo todo = new Todo((long)1, "hanging leg raise", 0, false);
        Todo savedTodo = todoRepository.save(todo);

        Assertions.assertThat(todo).isSameAs(savedTodo);
        Assertions.assertThat(todo.getId()).isEqualTo(savedTodo.getId());
        Assertions.assertThat(todo.getDescription()).isEqualTo(savedTodo.getDescription());
        Assertions.assertThat(todo.getCreated_at()).isEqualTo(savedTodo.getCreated_at());
        Assertions.assertThat(todo.isIs_finished()).isEqualTo(savedTodo.isIs_finished());

        Assertions.assertThat(savedTodo.getId()).isNotNull();
        Assertions.assertThat(todoRepository.count()).isEqualTo(1);
    }

}
