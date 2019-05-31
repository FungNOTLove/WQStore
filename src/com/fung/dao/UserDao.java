package com.fung.dao;

import com.fung.domain.User;

public interface UserDao {
	
	int insertUserinfo(User user);
	
	User findUserByUserName(String username);
	
	User findUser(String username,String password);
}
