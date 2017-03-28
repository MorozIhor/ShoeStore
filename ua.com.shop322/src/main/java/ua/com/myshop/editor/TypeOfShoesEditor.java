package ua.com.myshop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.myshop.entity.TypeOfShoes;
import ua.com.myshop.service.TypeOfShoesService;

public class TypeOfShoesEditor extends PropertyEditorSupport{

	
	private TypeOfShoesService typeOfShoesService;

	public TypeOfShoesEditor(TypeOfShoesService typeOfShoesService) {
		this.typeOfShoesService = typeOfShoesService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TypeOfShoes typeOfShoes = typeOfShoesService.findOne(Integer.valueOf(text));
		setValue(typeOfShoes);
	}
	
	
}
