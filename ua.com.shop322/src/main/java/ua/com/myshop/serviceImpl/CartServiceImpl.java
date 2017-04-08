package ua.com.myshop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.myshop.dao.CartDao;
import ua.com.myshop.entity.Cart;
import ua.com.myshop.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao cartDao;

	@Override
	public void save(Cart cart) {
		cartDao.save(cart);		
	}

	@Override
	public Cart findOne(int id) {
		return cartDao.findOne(id);
	}

	@Override
	public List<Cart> findAll() {
		return cartDao.findAll();
	}

	@Override
	public void delete(int id) {
		cartDao.delete(id);		
	}
	
	
}
