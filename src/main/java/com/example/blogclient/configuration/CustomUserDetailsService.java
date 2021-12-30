package com.example.blogclient.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blogclient.model.ApiUser;
import com.example.blogclient.service.LoginService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 1) Authenticate client with API
		ApiUser apiUser = new ApiUser();
		apiUser.setUsername("jojo");
		apiUser.setPassword("password");
		loginService.login(apiUser);
		
		// 2) Authenticate user with client creating an inMemory user
		User webUser = new User(
				apiUser.getUsername(), 
				new BCryptPasswordEncoder().encode(apiUser.getPassword()),
				getGrantedAuthorities());
		return webUser;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authorities = 
				new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

}
