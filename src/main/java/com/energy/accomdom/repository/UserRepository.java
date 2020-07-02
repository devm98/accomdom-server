package com.energy.accomdom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.energy.accomdom.entity.User;
import com.energy.accomdom.entity.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.userRole = ?1 and u.status = ?2")
    List<User> findByUserRole(UserRole userRole, boolean status);

}
