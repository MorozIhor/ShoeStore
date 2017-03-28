package ua.com.myshop.service;

import java.util.List;

import ua.com.myshop.entity.TypeOfShoes;

public interface TypeOfShoesService {

	void save(TypeOfShoes typeOfShoes);
	List<TypeOfShoes> findAll();
	TypeOfShoes findOne(int id);
	void delete(int id);
}
