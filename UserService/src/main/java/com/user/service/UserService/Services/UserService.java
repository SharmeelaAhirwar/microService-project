package com.user.service.UserService.Services;

import java.util.List;

import com.user.service.UserService.Entity.User;

public interface UserService {
	
    User createUser(User user);
    
    List<User>getAllUser();
    
    User getUserById(String userId);
    
    User updateUser(User user,String userId);
    
    void deleteUser(String userId);
}
