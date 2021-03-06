package ua.com.myshop.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.myshop.dto.filter.SimpleFilter;
import ua.com.myshop.entity.Gender;
import ua.com.myshop.service.GenderService;
import ua.com.myshop.validator.GenderValidator;
import static ua.com.myshop.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/gender")
@SessionAttributes("gender")
public class GenderController {

	@Autowired
	private GenderService genderService;
	
	@InitBinder("gender")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new GenderValidator(genderService));
	}
	
	@ModelAttribute("gender")
	public Gender getForm(){
		return new Gender();
	}
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", genderService.findAll(filter, pageable));
		return "admin-gender";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("gender", genderService.findOne(id));
		return show(model, pageable, filter);
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		genderService.delete(id);
		return "redirect:/admin/gender"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("gender") @Valid Gender gender, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		genderService.save(gender);
		status.setComplete();
		return "redirect:/admin/gender"+getParams(pageable, filter);
	}
}
