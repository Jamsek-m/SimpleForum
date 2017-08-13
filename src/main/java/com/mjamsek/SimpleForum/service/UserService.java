package com.mjamsek.SimpleForum.service;

import java.util.List;

import com.mjamsek.SimpleForum.controller.wrapper.EditUserWrapper;
import com.mjamsek.SimpleForum.entity.User;

public interface UserService {

	public User findUserByUsername(String username);
	
	public void saveUser(User user);
	
	public long vrniStNepotrjenih();
	
	public List<User> findAll();
	
	public List<User> vrniVseNepotrjeneUporabnike();
	
	public User findById(int id);
	
	public void editUser(EditUserWrapper editUserWrapper);
	
	public void potrdiUporabnika(int id);
	
	public void disableUser(int id);
	
}
