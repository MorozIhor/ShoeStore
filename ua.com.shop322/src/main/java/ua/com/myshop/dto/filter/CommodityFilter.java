package ua.com.myshop.dto.filter;

import java.util.ArrayList;
import java.util.List;

public class CommodityFilter {

	private String min = "";
	
	private String max = "";

	private double minValue;
	
	private double maxValue;
	
	private List<Integer> brandId = new ArrayList<>();
	
	private List<Integer> genderId = new ArrayList<>();
	
	private List<Integer> seasonId = new ArrayList<>();
	
	private List<Integer> typeOfShoesId = new ArrayList<>();

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public List<Integer> getBrandId() {
		return brandId;
	}

	public void setBrandId(List<Integer> brandId) {
		this.brandId = brandId;
	}

	public List<Integer> getGenderId() {
		return genderId;
	}

	public void setGenderId(List<Integer> genderId) {
		this.genderId = genderId;
	}

	public List<Integer> getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(List<Integer> seasonId) {
		this.seasonId = seasonId;
	}

	public List<Integer> getTypeOfShoesId() {
		return typeOfShoesId;
	}

	public void setTypeOfShoesId(List<Integer> typeOfShoesId) {
		this.typeOfShoesId = typeOfShoesId;
	}
	
	
	
}
