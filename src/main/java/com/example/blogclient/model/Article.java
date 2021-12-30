package com.example.blogclient.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Article {

	private Integer article_id;
	private String content;
	private Date date_pub;
	private Author author;
	

	public void setCommentary(List<Commentary> commentary) {
		this.commentary = commentary;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	private List<Commentary> commentary = new ArrayList<>();   
	 
	public List<Commentary> getCommentary() 
	{
	      return commentary;
	}
	
	public Integer getArticle_id() 
	{
		return article_id;
	}
	
	public void setArticle_id(Integer article_id) 
	{
		this.article_id = article_id;
	}
	
	public String getContent() 
	{
		return content;
	}
	
	public void setContent(String content) 
	{
		this.content = content;
	}
	
	public Date getDate_pub() 
	{
		return date_pub;
	}
	
	public void setDate_pub(Date date_pub) 
	{
		this.date_pub = date_pub;
	}
}
