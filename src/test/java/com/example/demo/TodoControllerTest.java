package com.example.demo;

import com.example.demo.controller.TodoController;
import com.example.demo.dto.Todo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class TodoControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    TodoController todoController;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(
                todoController
        ).build();
    }

    @Test
    public void getmethod() throws Exception {
        List<Todo> todolist = new ArrayList<>();
        Todo todaytodo = new Todo((long) 1, "hanging leg raise", 0, false);
        todolist.add(todaytodo);
        given(this.todoController.listTodo()).willReturn(todolist);

        String id = "$.[?(@.id == '%s')]";
        String description = "$.[?(@.description == '%s')]";

        this.mvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(jsonPath(id, "1").exists())
                .andExpect(jsonPath(description, "hanging leg raise").exists())
                .andExpect(status().isOk());
    }
}