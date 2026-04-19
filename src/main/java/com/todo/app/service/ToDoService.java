package com.todo.app.service;

import com.todo.app.model.Todo;
import com.todo.app.model.TodoRequest;
import com.todo.app.model.TodoStatistics;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface TodoService {
    // Basic CRUD operations
    List<Todo> getAllTodos();
    Optional<Todo> getTodoById(Long id);
    Todo createTodo(TodoRequest todoRequest);
    Optional<Todo> updateTodo(Long id, TodoRequest todoDetails);
    boolean deleteTodo(Long id);

    // Search and filter operations
    List<Todo> searchTodosByTitle(String title);
    List<Todo> getTodosByCompletionStatus(boolean completed);
    List<Todo> getTodosByPriority(String priority);
    List<Todo> getTodosByCategory(String category);
    List<Todo> getTodosDueBefore(LocalDateTime dueDate);
    List<Todo> getTodosDueAfter(LocalDateTime dueDate);
    List<Todo> getOverdueTodos();

    // Statistics
    TodoStatistics getTodoStatistics();

    // Bulk operations
    void deleteCompletedTodos();
    List<Todo> markMultipleAsCompleted(List<Long> ids);
}
