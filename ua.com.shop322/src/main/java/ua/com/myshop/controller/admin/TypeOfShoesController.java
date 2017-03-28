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

import ua.com.myshop.entity.TypeOfShoes;
import ua.com.myshop.service.TypeOfShoesService;

@Controller
@RequestMapping("/admin/typeofshoes")
@SessionAttributes("typeOfShoes")
public class TypeOfShoesController {

	@Autowired
	private TypeOfShoesService typeOfShoesService;
	
	@ModelAttribute("typeOfShoes")
	public TypeOfShoes getForm(){
		return new TypeOfShoes();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("typesOfShoes", typeOfShoesService.findAll());
		return "admin-typeOfShoes";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("typeOfShoes", typeOfShoesService.findOne(id));
		return show(model);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		typeOfShoesService.delete(id);
		return "redirect:/admin/typeofshoes";
	}
	
	@PostMapping
	public String save(@ModelAttribute("typeOfShoes") TypeOfShoes typeOfShoes, SessionStatus status){
		typeOfShoesService.save(typeOfShoes);
		status.setComplete();
		return "redirect:/admin/typeofshoes";
	}
}
