package com.example.springbootmongodb.service;

import com.example.springbootmongodb.exception.TodoCollectionException;
import com.example.springbootmongodb.model.TodoDTO;

import javax.validation.ConstraintViolationException;

public interface TodoService {
    void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;
}
