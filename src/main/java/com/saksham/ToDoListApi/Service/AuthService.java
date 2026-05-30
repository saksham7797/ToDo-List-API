package com.saksham.ToDoListApi.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saksham.ToDoListApi.Config.JWTService;
import com.saksham.ToDoListApi.DTO.UserLogin;
import com.saksham.ToDoListApi.DTO.UserRegistration;
import com.saksham.ToDoListApi.DTO.UserResponse;
import com.saksham.ToDoListApi.Entity.Users;
import com.saksham.ToDoListApi.Repository.UsersRepository;

@Service
public class AuthService {

    private final PasswordEncoder hashing;
    
    private final AuthenticationManager auth;
    
    private final JWTService jWTService;

    private final UsersRepository userRepository;

    public AuthService(AuthenticationManager auth, PasswordEncoder hashing, JWTService jWTService, UsersRepository userRepository) {
        this.auth = auth;
        this.hashing = hashing;
        this.jWTService = jWTService;
        this.userRepository = userRepository;
    }


    public String userRegistration(UserRegistration userRegistration) {

        if(userRepository.existsByEmail(userRegistration.getEmail())) throw new RuntimeException("User Already Exists");

        Users newUser = new Users();
        newUser.setName(userRegistration.getName());
        newUser.setEmail(userRegistration.getEmail());
        newUser.setPassword(hashing.encode(userRegistration.getPassword()));
        userRepository.save(newUser);
        return "User Registered Sucessfully";
    }

    public UserResponse userLogin(UserLogin userLogin) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword());
   
        auth.authenticate(authenticationToken);

        String token = jWTService.tokenGenerator(userLogin.getEmail());

        UserResponse response = new UserResponse(token);
        return response;
    }   
    
}
