package com.saksham.ToDoListApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saksham.ToDoListApi.Entity.Todos;
import com.saksham.ToDoListApi.Entity.Users;


public interface TodosRepository extends JpaRepository<Todos, Integer>{
    List<Todos> findByUsers(Users users); 
}
