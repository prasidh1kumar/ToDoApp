package com.todo.app.service.impl;

import com.todo.app.model.Todo;
import com.todo.app.model.TodoRequest;
import com.todo.app.model.TodoStatistics;
import com.todo.app.repository.TodoRepository;
import com.todo.app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo createTodo(TodoRequest todoRequest) {
        Todo newTodo = new Todo();
        newTodo.setTitle(todoRequest.getTitle());
        newTodo.setDescription(todoRequest.getDescription());
        newTodo.setCompleted(todoRequest.isCompleted());
        newTodo.setPriority(todoRequest.getPriority());
        newTodo.setCategory(todoRequest.getCategory());
        newTodo.setDueDate(todoRequest.getDueDate());
        newTodo.setCreatedAt(LocalDateTime.now());
        newTodo.setUpdatedAt(LocalDateTime.now());
        return todoRepository.save(newTodo);
    }

    @Override
    public Optional<Todo> updateTodo(Long id, TodoRequest todoDetails) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(todoDetails.getTitle());
                    todo.setDescription(todoDetails.getDescription());
                    todo.setCompleted(todoDetails.isCompleted());
                    todo.setPriority(todoDetails.getPriority());
                    todo.setCategory(todoDetails.getCategory());
                    todo.setDueDate(todoDetails.getDueDate());
                    todo.setUpdatedAt(LocalDateTime.now());
                    return todoRepository.save(todo);
                });
    }

    @Override
    public boolean deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            return false;
        }
        todoRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Todo> searchTodosByTitle(String title) {
        return todoRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Todo> getTodosByCompletionStatus(boolean completed) {
        return todoRepository.findByCompleted(completed);
    }

    @Override
    public List<Todo> getTodosByPriority(String priority) {
        return todoRepository.findByPriority(priority);
    }

    @Override
    public List<Todo> getTodosByCategory(String category) {
        return todoRepository.findByCategory(category);
    }

    @Override
    public List<Todo> getTodosDueBefore(LocalDateTime dueDate) {
        return todoRepository.findByDueDateBefore(dueDate);
    }

    @Override
    public List<Todo> getTodosDueAfter(LocalDateTime dueDate) {
        return todoRepository.findByDueDateAfter(dueDate);
    }

    @Override
    public List<Todo> getOverdueTodos() {
        return todoRepository.findOverdueTodos(LocalDateTime.now());
    }

    @Override
    public TodoStatistics getTodoStatistics() {
        long totalTodos = todoRepository.count();
        long completedTodos = todoRepository.countByCompleted(true);
        long pendingTodos = todoRepository.countByCompleted(false);
        long overdueTodos = todoRepository.findOverdueTodos(LocalDateTime.now()).size();
        long highPriorityTodos = todoRepository.countByPriority("HIGH");
        long mediumPriorityTodos = todoRepository.countByPriority("MEDIUM");
        long lowPriorityTodos = todoRepository.countByPriority("LOW");

        return new TodoStatistics(totalTodos, completedTodos, pendingTodos,
                                overdueTodos, highPriorityTodos, mediumPriorityTodos, lowPriorityTodos);
    }

    @Override
    public void deleteCompletedTodos() {
        todoRepository.deleteByCompleted(true);
    }

    @Override
    public List<Todo> markMultipleAsCompleted(List<Long> ids) {
        return ids.stream()
                .map(id -> todoRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(todo -> !todo.isCompleted())
                .map(todo -> {
                    todo.setCompleted(true);
                    todo.setUpdatedAt(LocalDateTime.now());
                    return todoRepository.save(todo);
                })
                .collect(Collectors.toList());
    }
}
