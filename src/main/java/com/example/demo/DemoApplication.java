package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static HashMap<String,List<HashMap<String,Object>>> board = new HashMap<>();
	public static String username = "TestUser";
	public static List<HashMap<String,Object>> todolist = new ArrayList<>();
	public static HashMap<String,Object> todo;

	public static void main(String[] args) {
		Properties defaultProperties = new Properties();
		defaultProperties.put("server.port","12345" );
		defaultProperties.put("server.address", "0.0.0.0");
		todo = new HashMap<>();
		todo.put("id", 1);
		todo.put("description", "squat");
		todolist.add(todo);
		todo = new HashMap<>();
		todo.put("id", 2);
		todo.put("description", "hanging leg raise");
		todolist.add(todo);

		board.put(username, todolist);
		SpringApplication sa = new SpringApplication(DemoApplication.class);
		sa.setDefaultProperties(defaultProperties);
		sa.run();
	}
	@GetMapping("/Todolist")
	public List<HashMap<String,Object>> Todolist(@RequestParam("name") String name) {
		return board.get(name);
	}
}