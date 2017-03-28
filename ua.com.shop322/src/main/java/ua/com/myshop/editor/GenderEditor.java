package ua.com.myshop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.myshop.entity.Gender;
import ua.com.myshop.service.GenderService;

public class GenderEditor extends PropertyEditorSupport{

	
	private GenderService genderService;

	public GenderEditor(GenderService genderService) {
		this.genderService = genderService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Gender gender = genderService.findOne(Integer.valueOf(text));
		setValue(gender);
	}
	
	
	
}
