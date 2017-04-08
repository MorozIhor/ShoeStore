package ua.com.myshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.myshop.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart>{

}
