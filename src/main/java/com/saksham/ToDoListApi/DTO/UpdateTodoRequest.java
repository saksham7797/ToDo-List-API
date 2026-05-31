package com.saksham.ToDoListApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoRequest {
    private int todoId;
    private String title;
    private String description;
    private boolean status;
}
