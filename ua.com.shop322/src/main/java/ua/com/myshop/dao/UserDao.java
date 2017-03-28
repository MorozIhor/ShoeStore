package ua.com.myshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.myshop.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{

	User findByEmail(String email);

}
