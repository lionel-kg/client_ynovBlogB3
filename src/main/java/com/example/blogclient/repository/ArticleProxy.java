package com.example.blogclient.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.blogclient.ApiProperties;
import com.example.blogclient.TokenContext;
import com.example.blogclient.model.Article;

@Component
public class ArticleProxy {
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
	
	public List<Article> getArticles() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Article>> response =
				restTemplate.exchange(
						props.getPublicurl() + "/articles", 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						new ParameterizedTypeReference<List<Article>>() {}
					);
		return response.getBody();
	}

	public Article getArticleById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Article> response =
				restTemplate.exchange(
						props.getPublicurl() + "/article/" + id, 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						Article.class);
		return response.getBody();
	}
	
	public void save(Article article) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<Article> request = new HttpEntity<Article>(article, createTokenHeaders());
		
		restTemplate.exchange(
				props.getUrl() + "/article/add",
				HttpMethod.POST,
				request,
				Article.class				
				);
	}

	/* public void deleteArticle(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.exchange(
				props.getPublicurl() + "/article/" + id, 
				HttpMethod.DELETE, 
				new HttpEntity<>(createTokenHeaders()), 
				Article.class);
	}	*/
}
