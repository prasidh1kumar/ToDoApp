package com.todo.app.service.impl;

import com.todo.app.model.Todo;
import com.todo.app.repository.TodoRepository;
import com.todo.app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;


    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(String title) {
        Todo newTodo = new Todo(null, title, false);
        return todoRepository.save(newTodo);
    }

    public Optional<Todo> updateTodo(Long id, Todo todoDetails) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(todoDetails.getTitle());
                    todo.setCompleted(todoDetails.isCompleted());
                    return todoRepository.save(todo);
                });
    }

    public boolean deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            return false;
        }
        todoRepository.deleteById(id);
        return true;
    }
}