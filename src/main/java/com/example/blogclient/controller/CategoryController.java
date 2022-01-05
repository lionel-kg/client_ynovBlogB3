package com.example.blogclient.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.blogclient.model.ApiUser;
import com.example.blogclient.model.Author;
import com.example.blogclient.model.Category;
import com.example.blogclient.service.CategoryService;
import com.example.blogclient.service.LoginService;

@Controller
@RequestMapping("/public")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/*@Autowired LoginService loginService;
	
	public void login()
    {
        ApiUser user = new ApiUser();
        user.setUsername("user");
        user.setPassword("user");
        loginService.login(user);


    }
	*/
	
	@GetMapping("/categories")
	public String categoriesPage(Model model, HttpSession session) {
		
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		return "categories";
	}
	
	@GetMapping("/category/{id}")
	public String categoryPage(@PathVariable(name = "id") Integer id, Model model) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		return "category";
	}
	
	@PostMapping("/category/add")
	public ModelAndView createNewCategory(@ModelAttribute Category category) {
		categoryService.save(category);
		return new ModelAndView("redirect:/public/categories");
	}
	
	@GetMapping("/newCategory")
	public String newCategoryPage(Model model) {
		model.addAttribute("category", new Category());
		return "addCategory";
	}
	
	/*@GetMapping("/category/del/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") Integer id) {
		categoryService.deleteCategory(id);
		return new ModelAndView("redirect:/public/categories");
	}*/
}
