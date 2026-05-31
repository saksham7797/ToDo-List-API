package com.saksham.ToDoListApi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saksham.ToDoListApi.DTO.UserLogin;
import com.saksham.ToDoListApi.DTO.UserRegistration;
import com.saksham.ToDoListApi.DTO.UserResponse;
import com.saksham.ToDoListApi.Service.AuthService;



@RestController
@RequestMapping("/todo/auth")
public class AuthController{
    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistration userRegistration) {
        authService.userRegistration(userRegistration);
        return ResponseEntity.ok("Registration Successful!");
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLogin userLogin) {
        return ResponseEntity.ok(authService.userLogin(userLogin));
    }    
}