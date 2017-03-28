package ua.com.myshop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.myshop.dao.TypeOfShoesDao;
import ua.com.myshop.entity.TypeOfShoes;
import ua.com.myshop.service.TypeOfShoesService;

@Service
public class TypeOfShoesServiceImpl implements TypeOfShoesService {

	@Autowired
	private TypeOfShoesDao typeOfShoesDao;
	
	public void save(TypeOfShoes typeOfShoes) {
		typeOfShoesDao.save(typeOfShoes);
	}

	public List<TypeOfShoes> findAll() {
		return typeOfShoesDao.findAll();
	}

	public TypeOfShoes findOne(int id) {
		return typeOfShoesDao.findOne(id);
	}

	public void delete(int id) {
		typeOfShoesDao.delete(id);
	}

}
