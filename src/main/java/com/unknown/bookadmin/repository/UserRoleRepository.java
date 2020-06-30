package com.unknown.bookadmin.repository;

import com.unknown.bookadmin.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRoleName(String roleName);
}
