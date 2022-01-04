package com.example.blogclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogclient.model.Author;
import com.example.blogclient.repository.AuthorProxy;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorProxy authorProxy;
	
	public Author getAuthorByUsername(String username) {
		return authorProxy.getAuthorByName(username);
	}
	
	
}
