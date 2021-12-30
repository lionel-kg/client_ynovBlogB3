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
import com.example.blogclient.model.Article;
import com.example.blogclient.model.Category;
import com.example.blogclient.service.ArticleService;
import com.example.blogclient.service.CategoryService;
import com.example.blogclient.service.LoginService;

@Controller
@RequestMapping("public")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private LoginService loginService;
	
	public void login()
    {
        ApiUser user = new ApiUser();
        user.setUsername("jojo");
        user.setPassword("password");
        loginService.login(user);
    }
	
	@GetMapping("/articles")
	public String articlesPage(Model model, HttpSession session) {
		login();
		List<Article> articles = articleService.getArticles();
		model.addAttribute("articles", articles);
		return "articles";
	}
	
	@GetMapping("/article/{id}")
	public String articlePage(@PathVariable(name = "id") Integer id, Model model) {
		Article article = articleService.getArticleById(id);
		model.addAttribute("article", article);
		return "article";
	}
	
	@PostMapping("/article/add")
	public ModelAndView createNewCategory(@ModelAttribute Article article) {
		articleService.save(article);
		return new ModelAndView("redirect:/categories");
	}
	
	@GetMapping("/newArticle")
	public String newCategoryPage(Model model) {
		model.addAttribute("article", new Article());
		return "newArticle";
	}
}
