package com.todo.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoStatistics {
    private long totalTodos;
    private long completedTodos;
    private long pendingTodos;
    private long overdueTodos;
    private long highPriorityTodos;
    private long mediumPriorityTodos;
    private long lowPriorityTodos;
}
