package com.user.service.UserService.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.UserService.Entity.User;

public interface UserRepo extends JpaRepository<User, String> {

}
