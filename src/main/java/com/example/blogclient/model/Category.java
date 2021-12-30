package com.example.blogclient.model;

import java.util.ArrayList;
import java.util.List;


public class Category {
	
	private Integer categoryId;
	private String name;
	private List<Article> articles = new ArrayList<>();
	
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public Integer getCategoryId() 
	{
		return categoryId;
	}
	
	public void setCategoryId(Integer categoryId) 
	{
		this.categoryId = categoryId;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
}