package ua.com.myshop.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Season {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String season;
	
	@OneToMany(mappedBy="season", fetch=FetchType.LAZY)
	private List<Commodity> commodities;
	
	public Season() {
		// TODO Auto-generated constructor stub
	}

	public Season(String season) {
		super();
		this.season = season;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
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
		Season other = (Season) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
