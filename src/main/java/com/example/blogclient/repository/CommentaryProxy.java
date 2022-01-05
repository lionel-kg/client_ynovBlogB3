package com.example.blogclient.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.blogclient.ApiProperties;
import com.example.blogclient.TokenContext;
import com.example.blogclient.model.Article;
import com.example.blogclient.model.Commentary;

@Component
public class CommentaryProxy {
	
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
	
	public void save(Commentary commentary ) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<Commentary> request = new HttpEntity<Commentary>(commentary, createTokenHeaders());
		
		restTemplate.exchange(
				props.getUrl() + "/commentary/add",
				HttpMethod.POST,
				request,
				Commentary.class				
				);
	}

	/*public void deleteCommentary(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.exchange(
				props.getPublicurl() + "/commentary/" + id, 
				HttpMethod.DELETE, 
				new HttpEntity<>(createTokenHeaders()), 
				Commentary.class);
	}	*/
}
