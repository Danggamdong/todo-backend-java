package com.example.demo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@Controller
public class TodoController {
	private static final TodoRepository repository = null;

	
//	TodoController(TodoRepository repository) {
//		this.repository = repository;
//	}


	public static void main(String[] args) {
		Todo todaytodo = new Todo((long)1, "hanging leg raise", 0, false);
		repository.save(todaytodo);
	}
	@GetMapping("/todos")
	List<Todo> listTodo() {
		return repository.findAll();
	}
	@PostMapping("/todos")
	HashMap newTodo(@RequestBody Todo newTodo) {
		HashMap hashmap = new HashMap<>();
		hashmap.put("id", repository.save(newTodo).getId());
		return hashmap;
	}
}