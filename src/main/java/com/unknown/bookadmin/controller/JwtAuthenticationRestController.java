package com.unknown.bookadmin.controller;

import com.unknown.bookadmin.entity.User;
import com.unknown.bookadmin.exception.AuthenticationException;
import com.unknown.bookadmin.model.request.JwtTokenRequest;
import com.unknown.bookadmin.model.response.JwtTokenResponse;
import com.unknown.bookadmin.service.JwtUserDetails;
import com.unknown.bookadmin.service.UserService;
import com.unknown.bookadmin.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JwtAuthenticationRestController {

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	private final AuthenticationManager authenticationManager;

	private final JwtTokenUtil jwtTokenUtil;

	private final UserService userService;

	private final UserDetailsService jwtInMemoryUserDetailsService;

	public JwtAuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService, UserDetailsService jwtInMemoryUserDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userService = userService;
		this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
	}

	@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
			throws AuthenticationException {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		User user = userService.findByUsername(authenticationRequest.getUsername());

		UserDetails userDetails = new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getUserRole().getRoleName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtTokenResponse(token, "Bearer", user ));
	}

	@RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		
		JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);
		
		User userRes = userService.findByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtTokenResponse(refreshedToken, "Bearer", userRes ));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("USER_DISABLED", e);
		}
	}
}
