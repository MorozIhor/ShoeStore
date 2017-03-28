package ua.com.myshop.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TypeOfShoes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String typeOfShoes;
	
	@OneToMany(mappedBy="typeOfShoes", fetch=FetchType.LAZY)
	private List<Commodity> commodities;
	
	public TypeOfShoes() {
		// TODO Auto-generated constructor stub
	}
	

	public TypeOfShoes(String typeOfShoes) {
		super();
		this.typeOfShoes = typeOfShoes;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeOfShoes() {
		return typeOfShoes;
	}

	public void setTypeOfShoes(String typeOfShoes) {
		this.typeOfShoes = typeOfShoes;
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
		TypeOfShoes other = (TypeOfShoes) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
