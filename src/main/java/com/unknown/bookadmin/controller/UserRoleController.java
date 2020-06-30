package com.unknown.bookadmin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.unknown.bookadmin.entity.UserRole;
import com.unknown.bookadmin.service.UserRoleService;

@RestController
@RequestMapping("user-roles")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }
    
    @GetMapping()
    public ResponseEntity<List<UserRole>> loadUserRoles() {
        List<UserRole> userRoles = userRoleService.loadUserRoles();
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createUserRole(@RequestBody UserRole userRole) {
        UserRole createdUserRole = userRoleService.save(userRole);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUserRole.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRole> updateUserRole(@PathVariable("id") long id, @RequestBody UserRole userRole) {
        userRole.setId(id);
        UserRole todoUpdated = userRoleService.save(userRole);
        return new ResponseEntity<>(todoUpdated, HttpStatus.OK);
    }


}
