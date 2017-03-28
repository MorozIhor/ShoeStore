package ua.com.myshop.dao;


import org.springframework.data.jpa.repository.JpaRepository;



import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.myshop.entity.Gender;

public interface GenderDao extends JpaRepository<Gender, Integer>, JpaSpecificationExecutor<Gender> {

	Gender findByGender(String name);

}
