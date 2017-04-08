package ua.com.myshop.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Commodity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Gender gender;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TypeOfShoes typeOfShoes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Season season;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Brand brand;
	
	@ManyToMany
	@JoinTable(name="cart_commodity", joinColumns=@JoinColumn(name="id_commodity"), inverseJoinColumns=@JoinColumn(name="id_cart"))
	private List<Cart> carts;
	
	private int version;
	
	public Commodity() {
		// TODO Auto-generated constructor stub
	}
	

	public Commodity(double price, Gender gender, TypeOfShoes typeOfShoes,
			Season season, Brand brand) {
		this.price = price;
		this.gender = gender;
		this.typeOfShoes = typeOfShoes;
		this.season = season;
		this.brand = brand;
	}



	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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


	public List<Cart> getCarts() {
		return carts;
	}


	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commodity other = (Commodity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
