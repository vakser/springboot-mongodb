package com.example.springbootmongodb.service;

import com.example.springbootmongodb.exception.TodoCollectionException;
import com.example.springbootmongodb.model.TodoDTO;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface TodoService {
    void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;
    List<TodoDTO> getAllTodos();
    TodoDTO getTodo(String id) throws TodoCollectionException;
    void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;
    void deleteTodoById(String id) throws TodoCollectionException;
}
