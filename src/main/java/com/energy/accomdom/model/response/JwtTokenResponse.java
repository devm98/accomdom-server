package com.energy.accomdom.model.response;

import java.io.Serializable;

import com.energy.accomdom.entity.User;

public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = 8317676219297719109L;

	private final String token;

	private final String tokenType;
	
	private final User user;
	

	public JwtTokenResponse(String token, String tokenType, User user) {
		super();
		this.token = token;
		this.tokenType = tokenType;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public User getUser() {
		return user;
	}

	
}