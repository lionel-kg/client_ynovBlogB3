package com.example.blogclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blogclient.model.Category;
import com.example.blogclient.repository.CategoryProxy;

@Service
public class CategoryService {
	@Autowired
	private CategoryProxy categoryProxy;
	
	public List<Category> getCategories() {
		return categoryProxy.getCategories();
	}

	public Category getCategoryById(Integer id) {
		return categoryProxy.getCategoryById(id);
	}

	public void save(Category category) {
		categoryProxy.save(category);		
	}
	
}
