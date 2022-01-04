package com.example.blogclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogclient.model.Article;
import com.example.blogclient.model.Author;
import com.example.blogclient.repository.ArticleProxy;

@Service
public class ArticleService {
	@Autowired
	private ArticleProxy articleProxy;
	
	public List<Article> getArticles() {
		return articleProxy.getArticles();
	}

	public Article getArticleById(Integer id) {
		return articleProxy.getArticleById(id);
	}

	public void save(Article article) {
		articleProxy.save(article);		
	}
	
	/*public void deleteArticle(Integer id) {
		articleProxy.deleteArticle(id);
	}*/
	/*public void addArticle(Article article, Author author, Integer id) {
		article.setAuthor(author);
		article.setCategory_Id(id);
		articleProxy.save(article);	
	}*/
	
}
