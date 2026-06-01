package com.saksham.ToDoListApi.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saksham.ToDoListApi.DTO.CreateTodoRequest;
import com.saksham.ToDoListApi.DTO.TodoResponse;
import com.saksham.ToDoListApi.DTO.UpdateTodoRequest;
import com.saksham.ToDoListApi.Service.TodoService;



@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody CreateTodoRequest createTodoRequest) {
        return ResponseEntity.ok(todoService.createTask(createTodoRequest));
    }  

    @PutMapping
    public ResponseEntity<TodoResponse> updateTodo(@RequestBody UpdateTodoRequest updateTodoRequest) {
        return ResponseEntity.ok(todoService.updateTask(updateTodoRequest));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@RequestBody int id) {
        return ResponseEntity.ok(todoService.deleteTask(id));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodo() {
        return ResponseEntity.ok(todoService.getAllTasks());
    }   
    
}
