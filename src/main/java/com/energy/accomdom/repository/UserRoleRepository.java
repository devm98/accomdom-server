package com.energy.accomdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.accomdom.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRoleName(String roleName);
}
