package com.unknown.bookadmin.service;

import java.util.List;

import com.unknown.bookadmin.entity.UserRole;

public interface UserRoleService {

	List<UserRole> loadUserRoles();
	
    UserRole findByRoleName(String roleName);

    UserRole save(UserRole userRole);

    UserRole findOne(Long id);

}