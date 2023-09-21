package com.example.springbootmongodb.controller;

import com.example.springbootmongodb.model.TodoDTO;
import com.example.springbootmongodb.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    private final TodoRepository todoRepo;

    public TodoController(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<TodoDTO> todos = todoRepo.findAll();
        if (!todos.isEmpty()) {
            return new ResponseEntity<>(todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
        try {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable String id) {
        Optional<TodoDTO> todoOptional = todoRepo.findById(id);
        if (todoOptional.isPresent()) {
            return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable String id, @RequestBody TodoDTO todo) {
        Optional<TodoDTO> todoOptional = todoRepo.findById(id);
        if (todoOptional.isPresent()) {
            TodoDTO todoToSave = todoOptional.get();
            todoToSave.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todoToSave.getCompleted());
            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
            todoToSave.setDescription(todo.getDescription() != null ? todo.getDescription() : todoToSave.getDescription());
            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoToSave);
            return new ResponseEntity<>(todoToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        Optional<TodoDTO> todoOptional = todoRepo.findById(id);
        if (todoOptional.isPresent()) {
            todoRepo.deleteById(id);
            return new ResponseEntity<>("Todo with id " + id + " successfully deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Todo with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
