package com.saksham.ToDoListApi.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {
    private int todoId;
    private String title;    
    private String description;    
    private boolean status;    
    private LocalDateTime createdAt;    
    private LocalDateTime updatedAt;
}
