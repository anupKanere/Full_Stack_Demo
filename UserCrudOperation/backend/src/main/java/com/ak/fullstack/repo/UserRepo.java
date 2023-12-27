package com.ak.fullstack.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.fullstack.model.User;

public interface UserRepo extends JpaRepository<User, Long >{

}
