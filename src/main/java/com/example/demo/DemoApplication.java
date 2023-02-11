package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static HashMap<String,List<String>> board = new HashMap<>();
	public static String username = "TestUser";
	public static String todo = "hanging leg raise";
	public static List<String> todolist = new ArrayList<>();

	public static void main(String[] args) {
		todolist.add(todo);
		todolist.add("squat");
		board.put(username, todolist);
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/Todolist")
	public List<String> Todolist(@RequestParam("name") String name) {
		return board.get(name);
	}
}