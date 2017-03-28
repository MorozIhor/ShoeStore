package ua.com.myshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.myshop.dto.filter.SimpleFilter;
import ua.com.myshop.entity.Gender;

public interface GenderService {

	void save(Gender gender);
	List<Gender> findAll();
	Gender findOne(int id);
	void delete(int id);
	Gender findByGender(String name);
	
	Page<Gender> findAll(SimpleFilter filter, Pageable pageable);
}
