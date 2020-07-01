package com.energy.accomdom.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.energy.accomdom.entity.User;
import com.energy.accomdom.model.request.UserModelRequest;
import com.energy.accomdom.service.UserService;

@RestController
@RequestMapping(value = "users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public ResponseEntity<List<User>> loadUsers(@RequestParam int page, @RequestParam int limit, @RequestParam int flag) {
		if(flag == 1) {
			List<User> userRoles = userService.loadUsers();
	        return new ResponseEntity<>(userRoles, HttpStatus.OK);
		} else {
			List<User> users = userService.loadUsers(page, limit);
			return new ResponseEntity<>(users ,HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		User user = userService.findOne(id);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Void> createUser(@RequestBody UserModelRequest userModelRequest) {
		User userCreated = userService.createUser(userModelRequest);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userCreated.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id,
			@RequestBody UserModelRequest userModelRequest) {
		User userUpdated = userService.updateUser(id, userModelRequest);
		return new ResponseEntity<>(userUpdated, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
