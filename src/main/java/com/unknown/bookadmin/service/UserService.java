package com.unknown.bookadmin.service;

import java.util.List;

import com.unknown.bookadmin.entity.User;
import com.unknown.bookadmin.entity.UserRole;
import com.unknown.bookadmin.model.request.UserModelRequest;

public interface UserService {

    List<User> loadUsers(int page, int limit);

    User findByUsername(String username);

    List<User> findByUserRole(UserRole userRole, boolean status);

    User createUser(UserModelRequest userModelRequest);

    User updateUser(long id, UserModelRequest userModelRequest);

    User findOne(Long id);

    void deleteUser(Long id);
}
