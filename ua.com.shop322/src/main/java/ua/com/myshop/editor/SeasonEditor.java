package ua.com.myshop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.myshop.entity.Season;
import ua.com.myshop.service.SeasonService;

public class SeasonEditor extends PropertyEditorSupport{

	
	private SeasonService seasonService;

	public SeasonEditor(SeasonService seasonService) {
		this.seasonService = seasonService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Season season = seasonService.findOne(Integer.valueOf(text));
		setValue(season);
	}
	
	
	
	
}
