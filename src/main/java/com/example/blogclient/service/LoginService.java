package com.example.blogclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogclient.TokenContext;
import com.example.blogclient.model.ApiUser;
import com.example.blogclient.repository.LoginProxy;

@Service
public class LoginService {

	@Autowired
	private TokenContext tokenContext;
	
	@Autowired
	private LoginProxy loginProxy;
	
	public void login(ApiUser user) {
		String token = loginProxy.login(user);
		tokenContext.setToken(token);
	}
	
}