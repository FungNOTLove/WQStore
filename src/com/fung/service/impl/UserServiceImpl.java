package com.fung.service.impl;

import com.fung.dao.UserDao;
import com.fung.dao.impl.UserDaoImpl;
import com.fung.domain.User;
import com.fung.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public int registerUser(User user) {
		UserDao ud = new UserDaoImpl();
		return ud.insertUserinfo(user);
	}

	@Override
	public User checkUserName(String username) {
		UserDao ud = new UserDaoImpl();
		return ud.findUserByUserName(username);
	}

	@Override
	public User login(String username, String password) {
		UserDao ud = new UserDaoImpl();
		return ud.findUser(username, password);
	}

}
