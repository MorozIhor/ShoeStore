package ua.com.myshop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.myshop.dto.filter.CommodityFilter;
import ua.com.myshop.service.BrandService;
import ua.com.myshop.service.CommodityService;
import ua.com.myshop.service.SeasonService;
import ua.com.myshop.service.TypeOfShoesService;

@Controller
@RequestMapping("/male")
public class MaleController {

	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private SeasonService seasonService;
	
	@Autowired
	private TypeOfShoesService typeOfShoesService;
	
	@ModelAttribute("filter")
	public CommodityFilter getFilter(){
		return new CommodityFilter();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault(size=9) Pageable pageable, @ModelAttribute("filter") CommodityFilter filter ){
		model.addAttribute("page", commodityService.findAllMale(pageable, filter));
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("seasons", seasonService.findAll());
		model.addAttribute("typesOfShoes", typeOfShoesService.findAll());
		return "user-male";
	}
	
	
}
