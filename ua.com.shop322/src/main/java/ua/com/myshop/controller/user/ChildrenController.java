package ua.com.myshop.controller.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.com.myshop.dto.filter.CommodityFilter;
import ua.com.myshop.entity.Cart;
import ua.com.myshop.entity.Commodity;
import ua.com.myshop.entity.User;
import ua.com.myshop.service.BrandService;
import ua.com.myshop.service.CommodityService;
import ua.com.myshop.service.SeasonService;
import ua.com.myshop.service.TypeOfShoesService;
import ua.com.myshop.util.ParamBuilder;

@Controller
@RequestMapping("/children")
@SessionAttributes("cart")
public class ChildrenController {

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
	
	@ModelAttribute("cart")
	public Cart getCart(){
		return new Cart();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault(size=9) Pageable pageable, 
			@ModelAttribute("filter") CommodityFilter filter, @ModelAttribute("cart") Cart cart){
		filter.getGenderId().add(3);
		model.addAttribute("page", commodityService.findAll(pageable, filter));
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("seasons", seasonService.findAll());
		model.addAttribute("typesOfShoes", typeOfShoesService.findAll());
		return "user-children";
	}
	
	@PostMapping("/addtocart/{id}")
	public String addToCart(Model model, @PageableDefault(size=9) Pageable pageable,
			@PathVariable int id, @ModelAttribute("cart") Cart cart,
			@ModelAttribute("filter") CommodityFilter filter) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		cart.setUser(user);
		Commodity comm = commodityService.findOne(id);
		if (cart.getCommodities() == null)
			cart.setCommodities(new ArrayList<Commodity>());
//		if (!cart.getCommodities().contains(comm))
			cart.getCommodities().add(comm);
		return "redirect:/children"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, CommodityFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder buffer =new StringBuilder(page);
		if(!filter.getMax().isEmpty()){
			buffer.append("&max=");
			buffer.append(filter.getMax());
		}
		if(!filter.getMin().isEmpty()){
			buffer.append("&min=");
			buffer.append(filter.getMin());
		}
		if(!filter.getBrandId().isEmpty()){
			for (Integer id : filter.getBrandId()) {
				buffer.append("&brandId=");
				buffer.append(id);
			}
		}
		if(!filter.getGenderId().isEmpty()){
			for (Integer id : filter.getGenderId()) {
				buffer.append("&genderId=");
				buffer.append(id);
			}
		}
		if(!filter.getSeasonId().isEmpty()){
			for (Integer id : filter.getSeasonId()) {
				buffer.append("&seasonId=");
				buffer.append(id);
			}
		}
		if(!filter.getTypeOfShoesId().isEmpty()){
			for (Integer id : filter.getTypeOfShoesId()) {
				buffer.append("&typeOfShoesId=");
				buffer.append(id);
			}
		}
		return buffer.toString();
	}
	
}
