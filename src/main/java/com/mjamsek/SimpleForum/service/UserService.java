package com.mjamsek.SimpleForum.service;

import com.mjamsek.SimpleForum.entity.User;

public interface UserService {

	public User findUserByUsername(String username);
	
	public void saveUser(User user);
	
}
