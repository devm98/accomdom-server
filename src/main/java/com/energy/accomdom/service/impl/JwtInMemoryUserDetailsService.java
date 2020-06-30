package com.energy.accomdom.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.energy.accomdom.entity.User;
import com.energy.accomdom.entity.UserRole;
import com.energy.accomdom.service.JwtUserDetails;
import com.energy.accomdom.service.UserRoleService;
import com.energy.accomdom.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {
	private final UserService userService;
	private final UserRoleService userRoleService;

	public JwtInMemoryUserDetailsService(UserService userService, UserRoleService userRoleService) {
		this.userService = userService;
		this.userRoleService = userRoleService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserRole role = userRoleService.findByRoleName("admin");
		List<User> inMemoryUserList = userService.findByUserRole(role, true);

		Optional<User> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return new JwtUserDetails(findFirst.get().getId(), findFirst.get().getUsername(), findFirst.get().getPassword(), findFirst.get().getUserRole().getRoleName());
	}

}
