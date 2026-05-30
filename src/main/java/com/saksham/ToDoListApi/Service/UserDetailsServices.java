package com.saksham.ToDoListApi.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saksham.ToDoListApi.Repository.UsersRepository;

@Service
public class UserDetailsServices implements UserDetailsService{

    private final UsersRepository usersRepository;

    public UserDetailsServices(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found in database: " + username));
    }
    
}