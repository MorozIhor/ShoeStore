package ua.com.myshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.myshop.dto.filter.CommodityFilter;
import ua.com.myshop.dto.form.CommodityForm;
import ua.com.myshop.entity.Brand;
import ua.com.myshop.entity.Commodity;
import ua.com.myshop.entity.Gender;
import ua.com.myshop.entity.Season;
import ua.com.myshop.entity.TypeOfShoes;

public interface CommodityService {

	void save(CommodityForm  commodity);
	
	List<Commodity> findAll();
	
	Commodity findOne(int id);
	
	CommodityForm findForm(int id);
	
	void delete(int id);
	
	Commodity findUnique(Brand brand, Gender gender, Season season,
			TypeOfShoes typeOfShoes, String price);
	
	Page<Commodity> findAll(Pageable pageable, CommodityFilter filter);

}
