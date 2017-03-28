package ua.com.myshop.dao;



import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.myshop.entity.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {
	
	Brand findByBrand(String name);
	

}
