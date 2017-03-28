package ua.com.myshop.dto.form;

import org.springframework.web.multipart.MultipartFile;

import ua.com.myshop.entity.Brand;
import ua.com.myshop.entity.Gender;
import ua.com.myshop.entity.Season;
import ua.com.myshop.entity.TypeOfShoes;

public class CommodityForm {

	
	private int id;
	
	private String price;
	
	private Gender gender;
	
	private TypeOfShoes typeOfShoes;
	
	private Season season;
	
	private Brand brand;
	
	private MultipartFile file;
	
	

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public TypeOfShoes getTypeOfShoes() {
		return typeOfShoes;
	}

	public void setTypeOfShoes(TypeOfShoes typeOfShoes) {
		this.typeOfShoes = typeOfShoes;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	
}
