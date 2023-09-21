package com.example.springbootmongodb.repository;

import com.example.springbootmongodb.model.TodoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TodoRepository extends MongoRepository<TodoDTO, String> {
    @Query("{'todo': ?0}")
    Optional<TodoDTO> findByTodo(String todo);
}
