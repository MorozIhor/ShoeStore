package ua.com.myshop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.myshop.dao.CommodityDao;
import ua.com.myshop.dto.filter.CommodityFilter;
import ua.com.myshop.dto.form.CommodityForm;
import ua.com.myshop.entity.Brand;
import ua.com.myshop.entity.Commodity;
import ua.com.myshop.entity.Gender;
import ua.com.myshop.entity.Season;
import ua.com.myshop.entity.TypeOfShoes;
import ua.com.myshop.service.CommodityService;
import ua.com.myshop.service.FileWriter;
import ua.com.myshop.service.FileWriter.Folder;
import ua.com.myshop.specification.CommoditySpecification;

@Service
public class CommodityServiceImpl implements CommodityService{

	@Autowired
	private CommodityDao commodityDao;
	
	@Autowired
	private FileWriter fileWriter;
	
	public void save(CommodityForm form) {
		Commodity commodity = new Commodity();
		commodity.setId(form.getId());
		commodity.setBrand(form.getBrand());
		commodity.setGender(form.getGender());
		commodity.setSeason(form.getSeason());
		commodity.setTypeOfShoes(form.getTypeOfShoes());
		commodity.setPrice(Double.valueOf(form.getPrice().replace(',', '.')));
		commodityDao.saveAndFlush(commodity);
		if(fileWriter.write(Folder.COMMODITIES, form.getFile(), commodity.getId())){
			commodity.setVersion(commodity.getVersion()+1);
			commodityDao.save(commodity);
		}
	}

	public List<Commodity> findAll() {
		return commodityDao.findAll();
	}

	public Commodity findOne(int id) {
		return commodityDao.findOne(id);
	}

	public void delete(int id) {
		commodityDao.delete(id);
	}

	public CommodityForm findForm(int id) {
		Commodity commodity = findOne(id);
		CommodityForm form = new CommodityForm();
		form.setId(commodity.getId());
		form.setBrand(commodity.getBrand());
		form.setGender(commodity.getGender());
		form.setSeason(commodity.getSeason());
		form.setTypeOfShoes(commodity.getTypeOfShoes());
		form.setPrice(String.valueOf(commodity.getPrice()));
		return form;
	}

	public Commodity findUnique(Brand brand, Gender gender, Season season,
			TypeOfShoes typeOfShoes, String price) {
		return commodityDao.findUnique(brand.getId(), gender.getId(), season.getId(), typeOfShoes.getId(), Double.valueOf(price.replace(',', '.')));
	}

	@Override
	public Page<Commodity> findAll(Pageable pageable, CommodityFilter filter) {
		return commodityDao.findAll(new CommoditySpecification(filter), pageable) ;
	}
	
}
