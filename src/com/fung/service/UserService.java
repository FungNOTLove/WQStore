package com.fung.service;

import com.fung.domain.User;

public interface UserService {
	int registerUser(User user);
	User checkUserName(String username);
	User login(String username,String password);
	
}
