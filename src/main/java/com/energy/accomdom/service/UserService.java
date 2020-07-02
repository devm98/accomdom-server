package com.energy.accomdom.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.energy.accomdom.entity.User;
import com.energy.accomdom.entity.UserRole;
import com.energy.accomdom.model.request.UserModelRequest;

public interface UserService {

    List<User> loadUsers(int page, int limit);
    
    List<User> loadUsers();

    User findByUsername(String username);
    
    List<User> getAllUserBy(String username);

    List<User> findByUserRole(UserRole userRole, boolean status);

    User createUser(UserModelRequest userModelRequest);

    User updateUser(long id, UserModelRequest userModelRequest);

    User findOne(Long id);

    void deleteUser(Long id);
}
