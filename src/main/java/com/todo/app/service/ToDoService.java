package com.todo.app.service;

import com.todo.app.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TodoService {
    List<Todo> getAllTodos();
    Optional<Todo> getTodoById(Long id);
    Todo createTodo(String title);
    Optional<Todo> updateTodo(Long id, Todo todoDetails);
    boolean deleteTodo(Long id);

}
