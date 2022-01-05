package com.example.blogclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.blogclient.model.Author;
import com.example.blogclient.model.Category;
import com.example.blogclient.model.Commentary;
import com.example.blogclient.service.CommentaryService;

@Controller
public class CommentaryController {
	
	@Autowired
	CommentaryService commentaryService;
	
	@PostMapping("/commentary/add")
	public ModelAndView createNewCommentary(@ModelAttribute Commentary commentary) {
		commentaryService.save(commentary);
		return new ModelAndView("redirect:/public/categories");
	}
	
	@GetMapping("/newCommentary/{id}")
	public String newCommentaryPage(Model model , @PathVariable("id") Integer id) {
		Commentary commentary = new Commentary();
		Author author = new Author();
		author.setUsername("thomas");
		commentary.setArticle_Id(id);
		commentary.setAuthor(author);
		model.addAttribute("commentary",commentary);
		return "addCommentary";
	}
	
}
