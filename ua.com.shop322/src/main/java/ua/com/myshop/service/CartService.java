package ua.com.myshop.service;

import java.util.List;

import ua.com.myshop.entity.Cart;

public interface CartService {

	void save(Cart cart);
	
	Cart findOne(int id);
	
	List<Cart> findAll();
	
	void delete(int id);
}
