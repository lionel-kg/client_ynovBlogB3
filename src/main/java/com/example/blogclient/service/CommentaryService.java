package com.example.blogclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogclient.model.Category;
import com.example.blogclient.model.Commentary;
import com.example.blogclient.repository.CommentaryProxy;

@Service
public class CommentaryService {
	
	@Autowired
	CommentaryProxy commentaryProxy;
	
	public void save(Commentary commentary) {
		commentaryProxy.save(commentary);		
	}
}
