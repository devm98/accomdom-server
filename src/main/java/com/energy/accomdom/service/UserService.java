package com.energy.accomdom.service;

import java.util.List;

import com.energy.accomdom.entity.User;
import com.energy.accomdom.entity.UserRole;
import com.energy.accomdom.model.request.UserModelRequest;

public interface UserService {

    List<User> loadUsers(int page, int limit);

    User findByUsername(String username);

    List<User> findByUserRole(UserRole userRole, boolean status);

    User createUser(UserModelRequest userModelRequest);

    User updateUser(long id, UserModelRequest userModelRequest);

    User findOne(Long id);

    void deleteUser(Long id);
}
