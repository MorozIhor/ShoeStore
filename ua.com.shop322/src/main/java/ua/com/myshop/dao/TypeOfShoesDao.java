package ua.com.myshop.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.myshop.entity.TypeOfShoes;


public interface TypeOfShoesDao extends JpaRepository<TypeOfShoes, Integer> {

}
