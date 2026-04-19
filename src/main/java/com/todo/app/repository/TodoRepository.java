package com.todo.app.repository;

import com.todo.app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // Search todos by title (case insensitive)
    List<Todo> findByTitleContainingIgnoreCase(String title);

    // Filter by completion status
    List<Todo> findByCompleted(boolean completed);

    // Filter by priority
    List<Todo> findByPriority(String priority);

    // Filter by category
    List<Todo> findByCategory(String category);

    // Find todos due before a certain date
    List<Todo> findByDueDateBefore(LocalDateTime dueDate);

    // Find todos due after a certain date
    List<Todo> findByDueDateAfter(LocalDateTime dueDate);

    // Find overdue todos
    @Query("SELECT t FROM Todo t WHERE t.dueDate < :currentDate AND t.completed = false")
    List<Todo> findOverdueTodos(@Param("currentDate") LocalDateTime currentDate);

    // Count todos by completion status
    long countByCompleted(boolean completed);

    // Count todos by priority
    long countByPriority(String priority);

    // Delete completed todos
    void deleteByCompleted(boolean completed);
}
