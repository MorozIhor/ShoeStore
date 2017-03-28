package ua.com.myshop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.myshop.entity.Brand;
import ua.com.myshop.service.BrandService;



public class BrandEditor extends PropertyEditorSupport {

	
	private BrandService brandService;

	public BrandEditor(BrandService brandService) {
		this.brandService = brandService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Brand brand = brandService.findOne(Integer.valueOf(text));
		setValue(brand);
	}
	
	
}
