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

import ua.com.myshop.dto.filter.CommodityFilter;
import ua.com.myshop.dto.form.CommodityForm;
import ua.com.myshop.editor.BrandEditor;
import ua.com.myshop.editor.GenderEditor;
import ua.com.myshop.editor.SeasonEditor;
import ua.com.myshop.editor.TypeOfShoesEditor;
import ua.com.myshop.entity.Brand;
import ua.com.myshop.entity.Gender;
import ua.com.myshop.entity.Season;
import ua.com.myshop.entity.TypeOfShoes;
import ua.com.myshop.service.BrandService;
import ua.com.myshop.service.CommodityService;
import ua.com.myshop.service.GenderService;
import ua.com.myshop.service.SeasonService;
import ua.com.myshop.service.TypeOfShoesService;
import ua.com.myshop.util.ParamBuilder;
import ua.com.myshop.validator.CommodityValidator;

@Controller
@RequestMapping("/admin/commodity")
@SessionAttributes("commodity")
public class CommodityController {

	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private GenderService genderService;
	
	@Autowired
	private SeasonService seasonService;
	 
	@Autowired
	private TypeOfShoesService typeOfShoesService;
	
	@InitBinder("commodity")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(Gender.class, new GenderEditor(genderService));
		binder.registerCustomEditor(Season.class, new SeasonEditor(seasonService));
		binder.registerCustomEditor(TypeOfShoes.class, new TypeOfShoesEditor(typeOfShoesService));
		binder.setValidator(new CommodityValidator(commodityService));
	}
	
	
	@ModelAttribute("commodity")
	public CommodityForm getForm(){
		return new CommodityForm();
	}
	
	@ModelAttribute("filter")
	public CommodityFilter getFilter(){
		return new CommodityFilter();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") CommodityFilter filter){
		model.addAttribute("page", commodityService.findAll(pageable, filter));
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("genders", genderService.findAll());
		model.addAttribute("seasons", seasonService.findAll());
		model.addAttribute("typesOfShoes", typeOfShoesService.findAll());
		return "admin-commodity";
	}
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") CommodityFilter filter){
		model.addAttribute("commodity", commodityService.findForm(id));
		return show(model, pageable, filter);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") CommodityFilter filter){
		commodityService.delete(id);
		return "redirect:/admin/commodity"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("commodity") @Valid CommodityForm commodity, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") CommodityFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		commodityService.save(commodity);
		status.setComplete();
		return "redirect:/admin/commodity"+getParams(pageable, filter);
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
