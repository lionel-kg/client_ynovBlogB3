package com.example.blogclient.model;

import java.util.Date;


public class Commentary {
	
	private int comment_id;
	private Author author;
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	public Integer article_Id;

	public Integer getArticle_Id() {
		return article_Id;
	}

	public void setArticle_Id(Integer article_Id) {
		this.article_Id = article_Id;
	}
	private Date date_pub = new Date();
	private String content;
	
	public int getComment_id() 
	{
		return comment_id;
	}
	
	public void setComment_id(int comment_id) 
	{
		this.comment_id = comment_id;
	}
	
	public Date getDate_pub() 
	{
		return date_pub;
	}
	
	public void setDate_pub(Date date_pub) 
	{
		this.date_pub = date_pub;
	}
	
	public String getContent() 
	{
		return content;
	}
	
	public void setContent(String content) 
	{
		this.content = content;
	}
	
}
