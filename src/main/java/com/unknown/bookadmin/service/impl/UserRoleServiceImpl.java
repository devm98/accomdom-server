package com.unknown.bookadmin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unknown.bookadmin.entity.UserRole;
import com.unknown.bookadmin.repository.UserRoleRepository;
import com.unknown.bookadmin.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> loadUserRoles() {
        return (List<UserRole>) userRoleRepository.findAll();
    }
    
    @Override
    public UserRole findByRoleName(String roleName) {
        return userRoleRepository.findByRoleName(roleName);
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }


    @Override
    public UserRole findOne(Long id) {
        return userRoleRepository.findById(id).get();
    }

}
