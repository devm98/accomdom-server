package com.energy.accomdom.service;

import java.util.List;

import com.energy.accomdom.entity.UserRole;

public interface UserRoleService {

	List<UserRole> loadUserRoles();
	
    UserRole findByRoleName(String roleName);

    UserRole save(UserRole userRole);

    UserRole findOne(Long id);

}