package com.example.demo;

import com.example.demo.controller.TodoController;
import com.example.demo.dto.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @DisplayName("get test")
    public void getmethod() throws Exception {
        List<Todo> todolist = new ArrayList<>();
        Todo todaytodo = new Todo("1", "Todo", "hanging leg raise", 0, 0, false);
        todolist.add(todaytodo);
        given(this.todoController.listTodo()).willReturn(todolist);

        String id = "$.[?(@.id == '%s')]";
        String description = "$.[?(@.description == '%s')]";

        this.mvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(jsonPath(id, "1").exists())
                .andExpect(jsonPath(description, "hanging leg raise").exists())
                .andExpect(status().isOk());
    }

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    @DisplayName("update test")
    public void putmethod() throws Exception {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("description", "hanging leg raise");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(hashMap);

        this.mvc.perform(put("/todos/{id}", 1).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}
