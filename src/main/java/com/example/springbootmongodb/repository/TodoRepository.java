package com.example.springbootmongodb.repository;

import com.example.springbootmongodb.model.TodoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<TodoDTO, String> {
}
