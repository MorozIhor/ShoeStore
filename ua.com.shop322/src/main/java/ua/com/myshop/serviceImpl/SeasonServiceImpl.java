package ua.com.myshop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.myshop.dao.SeasonDao;
import ua.com.myshop.entity.Season;
import ua.com.myshop.service.SeasonService;

@Service
public class SeasonServiceImpl implements SeasonService {

	@Autowired
	private SeasonDao seasonDao;
	
	public void save(Season season) {
		seasonDao.save(season);		
	}

	public List<Season> findAll() {
		return seasonDao.findAll();
	}

	public Season findOne(int id) {
		return seasonDao.findOne(id);
	}

	public void delete(int id) {
		seasonDao.delete(id);
	}

	
}
