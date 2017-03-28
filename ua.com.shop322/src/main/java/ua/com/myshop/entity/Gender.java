package ua.com.myshop.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Gender {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String gender;
	
	@OneToMany(mappedBy="gender", fetch=FetchType.LAZY)
	private List<Commodity> commodities;
	
	public Gender() {
		// TODO Auto-generated constructor stub
	}
	

	public Gender(String gender) {
		super();
		this.gender = gender;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
		Gender other = (Gender) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
