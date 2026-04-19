package com.todo.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {
    private String title;
    private String description;
    private boolean completed;
    private String priority;
    private String category;
    private LocalDateTime dueDate;
}
