package com.example.blogclient.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.example.blogclient.model.Author;
import com.example.blogclient.model.Category;
import com.example.blogclient.service.ArticleService;
import com.example.blogclient.service.AuthorService;
import com.example.blogclient.service.CategoryService;
import com.example.blogclient.service.LoginService;

@Controller
@RequestMapping("public")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AuthorService authorService;
	
	public Author getAuthor() {
		String username = "null"; 
		SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        
        Author author = new Author();


        if(authentication != null)
        {
            if(authentication.getPrincipal() instanceof UserDetails)
            {
                username = ((UserDetails) authentication.getPrincipal()).getUsername();
                author = authorService.getAuthorByUsername(username) ;
            }

            else if (authentication.getPrincipal() instanceof String)
                  username = (String) authentication.getPrincipal();
        }
        return author;
	}
	
	@GetMapping("/articles")
	public String articlesPage(Model model, HttpSession session) {
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
	public ModelAndView createNewArticle(@ModelAttribute Article article, @ModelAttribute("category") Category category) {
		Author author = getAuthor();
		Integer category_id = category.getCategoryId();
		article.setAuthor(author);
		article.setCategory_Id(category_id);
		articleService.save(article);
		return new ModelAndView("redirect:/public/categories");
	}
	
	@GetMapping("/newArticle/{id}")
	public String newArticlePage(Model model, @PathVariable("id") Integer id) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("article", new Article());
		model.addAttribute("category",category);
		return "addArticle";
	}
	
	/*@GetMapping("/article/del/{id}") 
	public ModelAndView deleteArticle(@PathVariable("id") Integer id){
		articleService.deleteArticle(id);
		return new ModelAndView("redirect:/public/articles");
	}*/
}
