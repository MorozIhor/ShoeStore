package ua.com.myshop.service;

import java.util.List;

import ua.com.myshop.entity.Season;

public interface SeasonService {

	void save(Season season);
	List<Season> findAll();
	Season findOne(int id);
	void delete(int id);
}
