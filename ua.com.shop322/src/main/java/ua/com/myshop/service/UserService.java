package ua.com.myshop.service;

import ua.com.myshop.entity.User;

public interface UserService {

	void save(User user);

	User findByEmail(String email);
}
