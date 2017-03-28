package ua.com.myshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.myshop.entity.Season;
import ua.com.myshop.service.SeasonService;

@Controller
@RequestMapping("/admin/season")
@SessionAttributes("season")
public class SeasonController {

	@Autowired
	private SeasonService seasonService;
	
	@ModelAttribute("season")
	public Season getForm(){
		return new Season();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("seasons", seasonService.findAll());
		return "admin-season";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("season", seasonService.findOne(id));
		return show(model);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		seasonService.delete(id);
		return "redirect:/admin/season";
	}
	
	@PostMapping
	public String save(@ModelAttribute("season") Season season, SessionStatus status){
		seasonService.save(season);
		status.setComplete();
		return "redirect:/admin/season";
	}
}
