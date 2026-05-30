package com.saksham.ToDoListApi.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saksham.ToDoListApi.DTO.DeleteAccountRequest;
import com.saksham.ToDoListApi.DTO.UpdatePasswordRequest;
import com.saksham.ToDoListApi.DTO.UserDataResponse;
import com.saksham.ToDoListApi.Entity.Users;
import com.saksham.ToDoListApi.Repository.UsersRepository;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    public UserDataResponse getUserProfile() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String name = ((Users)user).getName();
        String email = ((Users)user).getEmail();

        return new UserDataResponse(name, email);
    }

    public String updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = ((Users)obj);

        if(!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) throw new RuntimeException("Invalid Old Password");

        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
        usersRepository.save(user);
        return "Password Updated";
    }

    public String deleteAccount(DeleteAccountRequest deleteAccountRequest) {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = ((Users)obj);

        if(!passwordEncoder.matches(deleteAccountRequest.getPassword(), user.getPassword())) throw new RuntimeException("Invalid Password");

        user.setActive(false);
        usersRepository.save(user);
        return "Account Deleted Successfully!";
    }
}
