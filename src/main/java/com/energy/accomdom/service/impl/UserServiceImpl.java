package com.energy.accomdom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.energy.accomdom.entity.User;
import com.energy.accomdom.entity.UserRole;
import com.energy.accomdom.exception.ServiceException;
import com.energy.accomdom.model.request.UserModelRequest;
import com.energy.accomdom.repository.UserRepository;
import com.energy.accomdom.repository.UserRoleRepository;
import com.energy.accomdom.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;

	public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public List<User> loadUsers(int page, int limit) {
		Pageable paging = PageRequest.of(page, limit);

		Page<User> pagedResult = userRepository.findAll(paging);

		return pagedResult.toList();
	}

	@Override
	public List<User> loadUsers() {
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findByUserRole(UserRole userRole, boolean status) {
		return userRepository.findByUserRole(userRole, status);
	}

	@Override
	public User createUser(UserModelRequest userModelRequest) {
		User localUser = userRepository.findByUsername(userModelRequest.getUsername());

		if (localUser != null) {
			LOG.info("User {} already exists. Nothing will be done.", localUser.getUsername());
			throw new ServiceException("User already exists.");
		} else {
			User user = new User();
			user.setUsername(userModelRequest.getUsername());
			user.setPassword(new BCryptPasswordEncoder().encode(userModelRequest.getPassword()));
			user.setEmail(userModelRequest.getEmail());
			user.setFullName(userModelRequest.getFullName());
			user.setBirthday(userModelRequest.getBirthday());
			user.setGender(userModelRequest.getGender());
			user.setPhone(userModelRequest.getPhone());
			user.setAddress(userModelRequest.getAddress());
			user.setStatus(true);

			UserRole role = userRoleRepository.findByRoleName(userModelRequest.getRoleName());
			user.setUserRole(role);

			localUser = userRepository.save(user);
		}

		return localUser;
	}

	@Override
	public User updateUser(long id, UserModelRequest userModelRequest) {
		User userUpdated = new User();
		userUpdated.setId(id);
		userUpdated.setUsername(userModelRequest.getUsername());
		userUpdated.setPassword(userModelRequest.getPassword());
		userUpdated.setEmail(userModelRequest.getEmail());
		userUpdated.setFullName(userModelRequest.getFullName());
		userUpdated.setBirthday(userModelRequest.getBirthday());
		userUpdated.setGender(userModelRequest.getGender());
		userUpdated.setPhone(userModelRequest.getPhone());
		userUpdated.setAddress(userModelRequest.getAddress());
		userUpdated.setStatus(true);

		UserRole role = userRoleRepository.findByRoleName(userModelRequest.getRoleName());
		userUpdated.setUserRole(role);

		userUpdated = userRepository.save(userUpdated);
		return userUpdated;
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	

}
