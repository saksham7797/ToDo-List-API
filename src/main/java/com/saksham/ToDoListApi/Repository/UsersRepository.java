package com.saksham.ToDoListApi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saksham.ToDoListApi.Entity.Users;


public interface UsersRepository extends JpaRepository<Users, String>{
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);
}
