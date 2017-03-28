package ua.com.myshop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.myshop.dao.GenderDao;
import ua.com.myshop.dto.filter.SimpleFilter;
import ua.com.myshop.entity.Gender;
import ua.com.myshop.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService{

	@Autowired
	private GenderDao genderDao;
	
	public void save(Gender gender) {
		genderDao.save(gender);		
	}

	public List<Gender> findAll() {
		return genderDao.findAll();
	}

	public Gender findOne(int id) {
		return genderDao.findOne(id);
	}

	public void delete(int id) {
		genderDao.delete(id);		
	}

	public Gender findByGender(String name) {
		return genderDao.findByGender(name);
	}

	@Override
	public Page<Gender> findAll(SimpleFilter filter, Pageable pageable) {
		return genderDao.findAll(findByNameLike(filter), pageable);
	}
	
	private Specification<Gender> findByNameLike(SimpleFilter filter){
		return (root, query, cb)-> {
			if(filter.getSearch().isEmpty())return null;
			return cb.like(cb.lower(root.get("gender")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
