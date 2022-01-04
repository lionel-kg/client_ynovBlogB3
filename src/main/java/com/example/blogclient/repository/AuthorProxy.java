package com.example.blogclient.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.blogclient.ApiProperties;
import com.example.blogclient.TokenContext;
import com.example.blogclient.model.Article;
import com.example.blogclient.model.Author;

@Component
public class AuthorProxy {
	@Autowired
	ApiProperties props;
	
	@Autowired
	TokenContext tokenContext;
	
	private HttpHeaders createTokenHeaders() {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				// utilisation du token qui est dans la classe ApiProperties
				String authHeader = "Bearer " + tokenContext.getToken();
				set("Authorization", authHeader);
				System.out.println("Provided token is : " + authHeader);
			}
		};
	}
	
	public Author getAuthorByName(String username) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Author> response =
				restTemplate.exchange(
						props.getPublicurl() + "/author/" + username, 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						Author.class);
		return response.getBody();
	}
}
