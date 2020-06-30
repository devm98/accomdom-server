package com.unknown.bookadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.unknown.bookadmin.entity.User;
import com.unknown.bookadmin.entity.UserRole;

public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.userRole = ?1 and u.status = ?2")
    List<User> findByUserRole(UserRole userRole, boolean status);

}
