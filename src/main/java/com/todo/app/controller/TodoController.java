package com.todo.app.controller;


import com.todo.app.model.Todo;
import com.todo.app.model.TodoRequest;
import com.todo.app.model.TodoStatistics;
import com.todo.app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // Basic CRUD operations
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoRequest todoRequest) {
        Todo savedTodo = todoService.createTodo(todoRequest);
        return ResponseEntity
                .created(URI.create("/api/todos/" + savedTodo.getId()))
                .body(savedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoRequest todoDetails) {
        return todoService.updateTodo(id, todoDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (!todoService.deleteTodo(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // Search and filter operations
    @GetMapping("/search")
    public List<Todo> searchTodos(@RequestParam String title) {
        return todoService.searchTodosByTitle(title);
    }

    @GetMapping("/filter/completed/{completed}")
    public List<Todo> getTodosByCompletionStatus(@PathVariable boolean completed) {
        return todoService.getTodosByCompletionStatus(completed);
    }

    @GetMapping("/filter/priority/{priority}")
    public List<Todo> getTodosByPriority(@PathVariable String priority) {
        return todoService.getTodosByPriority(priority.toUpperCase());
    }

    @GetMapping("/filter/category/{category}")
    public List<Todo> getTodosByCategory(@PathVariable String category) {
        return todoService.getTodosByCategory(category);
    }

    @GetMapping("/filter/due-before")
    public List<Todo> getTodosDueBefore(@RequestParam String dateTime) {
        LocalDateTime dueDate = LocalDateTime.parse(dateTime);
        return todoService.getTodosDueBefore(dueDate);
    }

    @GetMapping("/filter/due-after")
    public List<Todo> getTodosDueAfter(@RequestParam String dateTime) {
        LocalDateTime dueDate = LocalDateTime.parse(dateTime);
        return todoService.getTodosDueAfter(dueDate);
    }

    @GetMapping("/overdue")
    public List<Todo> getOverdueTodos() {
        return todoService.getOverdueTodos();
    }

    // Statistics
    @GetMapping("/statistics")
    public TodoStatistics getTodoStatistics() {
        return todoService.getTodoStatistics();
    }

    // Bulk operations
    @DeleteMapping("/completed")
    public ResponseEntity<Void> deleteCompletedTodos() {
        todoService.deleteCompletedTodos();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/bulk/complete")
    public ResponseEntity<List<Todo>> markMultipleAsCompleted(@RequestBody List<Long> ids) {
        List<Todo> updatedTodos = todoService.markMultipleAsCompleted(ids);
        return ResponseEntity.ok(updatedTodos);
    }
}
