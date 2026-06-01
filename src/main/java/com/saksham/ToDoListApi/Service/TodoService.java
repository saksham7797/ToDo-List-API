package com.saksham.ToDoListApi.Service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.saksham.ToDoListApi.DTO.CreateTodoRequest;
import com.saksham.ToDoListApi.DTO.TodoResponse;
import com.saksham.ToDoListApi.DTO.UpdateTodoRequest;
import com.saksham.ToDoListApi.Entity.Todos;
import com.saksham.ToDoListApi.Entity.Users;
import com.saksham.ToDoListApi.Repository.TodosRepository;

@Service
public class TodoService {

    private final TodosRepository todosRepository;

    public TodoService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    private Users getCurrentUser() {
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public TodoResponse createTask(CreateTodoRequest createTodoRequest) {
        Todos todo = new Todos();
        todo.setTitle(createTodoRequest.getTitle());
        todo.setDescription(createTodoRequest.getDescription());
        todo.setStatus(false);

        Users user = getCurrentUser();

        todo.setUsers(user);

        Todos savedTodo = todosRepository.save(todo);

        return new TodoResponse(savedTodo.getTodoId(), savedTodo.getTitle(), savedTodo.getDescription(), savedTodo.isStatus(), savedTodo.getCreatedAt(), savedTodo.getUpdatedAt());
    }

    public TodoResponse updateTask(UpdateTodoRequest updateTodoRequest) {

        Users user = getCurrentUser();
        Todos todo = todosRepository.findById(updateTodoRequest.getTodoId()).orElseThrow(() -> new RuntimeException("Task not found!"));

        if(!todo.getUsers().getUserId().equals(user.getUserId())) throw new RuntimeException("Invalid query!");

        todo.setTitle(updateTodoRequest.getTitle());
        todo.setDescription(updateTodoRequest.getDescription());
        todo.setStatus(updateTodoRequest.isStatus());
        
        Todos savedTodo = todosRepository.save(todo);

        return new TodoResponse(savedTodo.getTodoId(), savedTodo.getTitle(), savedTodo.getDescription(), savedTodo.isStatus(), savedTodo.getCreatedAt(), savedTodo.getUpdatedAt());
    }

    public List<TodoResponse> getAllTasks() {

        Users user = getCurrentUser();
        List<Todos> allTodos = todosRepository.findByUsers(user);

        return allTodos.stream().map(todo -> new TodoResponse(todo.getTodoId(), todo.getTitle(), todo.getDescription(), todo.isStatus(), todo.getCreatedAt(), todo.getUpdatedAt())).toList();
    }

    public String deleteTask(int todoId) {
        
        Users user = getCurrentUser();

        Todos todo = todosRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Task not found!"));

        if(!todo.getUsers().getUserId().equals(user.getUserId())) throw new RuntimeException("Invalid query!");

        todosRepository.delete(todo);
        return "Task Deleted Successfully";
    }
}
