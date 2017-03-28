package ua.com.myshop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.myshop.dao.BrandDao;
import ua.com.myshop.dto.filter.SimpleFilter;
import ua.com.myshop.entity.Brand;
import ua.com.myshop.service.BrandService;
import ua.com.myshop.service.FileWriter;
import ua.com.myshop.service.FileWriter.Folder;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private FileWriter fileWriter;
	
	public void save(Brand brand) {
		MultipartFile file = brand.getFile();
		brand = brandDao.saveAndFlush(brand);
		if(fileWriter.write(Folder.BRAND, file, brand.getId())){
			brand.setVersion(brand.getVersion()+1);
			brandDao.save(brand);
		}
	}

	public List<Brand> findAll() {
		return brandDao.findAll();
	}

	public Brand findOne(int id) {
		return brandDao.findOne(id);
	}

	public void delete(int id) {
		brandDao.delete(id);
	}

	public Brand findByBrand(String name) {
		return brandDao.findByBrand(name);
	}

	@Override
	public Page<Brand> findAll(SimpleFilter filter, Pageable pageable) {
		return brandDao.findAll(findByNameLike(filter), pageable);
	}

	private Specification<Brand> findByNameLike(SimpleFilter filter){
		return (root, query, cb)-> {
			if(filter.getSearch().isEmpty())return null;
			return cb.like(cb.lower(root.get("brand")), filter.getSearch().toLowerCase()+"%");
		};
	}
}
