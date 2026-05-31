package com.saksham.ToDoListApi.Controller;

import com.saksham.ToDoListApi.Service.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.saksham.ToDoListApi.DTO.UserDataResponse;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.saksham.ToDoListApi.DTO.DeleteAccountRequest;
import com.saksham.ToDoListApi.DTO.UpdatePasswordRequest;


@RestController
@RequestMapping("/todo/user")
public class UserController{
    
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDataResponse> getUserDetails() {
        return ResponseEntity.ok(userService.getUserProfile());
    }    

    @PutMapping("/updatepassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        return ResponseEntity.ok(userService.updatePassword(updatePasswordRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestBody DeleteAccountRequest deleteAccountRequest) {
        return ResponseEntity.ok(userService.deleteAccount(deleteAccountRequest));
    }

}