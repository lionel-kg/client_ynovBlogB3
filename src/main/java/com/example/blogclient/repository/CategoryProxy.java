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
import com.example.blogclient.model.Category;

@Component
public class CategoryProxy {
	
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
	
	public List<Category> getCategories() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Category>> response =
				restTemplate.exchange(
						props.getPublicurl() + "/categories", 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						new ParameterizedTypeReference<List<Category>>() {}
					);
		return response.getBody();
	}

	public Category getCategoryById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Category> response =
				restTemplate.exchange(
						props.getPublicurl() + "/category/" + id, 
						HttpMethod.GET, 
						new HttpEntity<>(createTokenHeaders()), 
						Category.class);
		return response.getBody();
	}
	
	public void save(Category category) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<Category> request = new HttpEntity<Category>(category, createTokenHeaders());
		
		restTemplate.exchange(
				props.getUrl() + "/category/add",
				HttpMethod.POST,
				request,
				Category.class				
				);
	}

	/*public void deleteCategory(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.exchange(
				props.getPublicurl() + "/category/" + id, 
				HttpMethod.DELETE, 
				new HttpEntity<>(createTokenHeaders()), 
				Category.class);
	}*/
	
}
