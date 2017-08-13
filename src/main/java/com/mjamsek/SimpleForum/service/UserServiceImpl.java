package com.mjamsek.SimpleForum.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mjamsek.SimpleForum.controller.wrapper.EditUserWrapper;
import com.mjamsek.SimpleForum.entity.Role;
import com.mjamsek.SimpleForum.entity.User;
import com.mjamsek.SimpleForum.repository.RoleRepository;
import com.mjamsek.SimpleForum.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	
	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setActive(0);
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public long vrniStNepotrjenih() {
		return userRepository.countUnconfirmedNumber();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> vrniVseNepotrjeneUporabnike() {
		return userRepository.getAllUnconfirmedUsers();
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public void editUser(EditUserWrapper editUserWrapper) {
		User user = userRepository.findById(editUserWrapper.getId());
		user.setDisplayName(editUserWrapper.getDisplayName());
		user.setUsername(editUserWrapper.getUsername());
		user.setRoles(new HashSet<Role>());
		for(int i = 0; i < editUserWrapper.getRoles().size(); i++) {
			String rola = editUserWrapper.getRoles().get(i);
			if(rola != null && !rola.equals("")) {
				int role_id = Integer.parseInt(editUserWrapper.getRoles().get(i));
				Role role = roleRepository.findById(role_id);
				user.getRoles().add(role);
			}
		}
		if(editUserWrapper.getPassword() != null && !editUserWrapper.getPassword().equals("")) {
			user.setPassword(bCrypt.encode(editUserWrapper.getPassword()));
		}
		userRepository.save(user);
	}

	@Override
	public void potrdiUporabnika(int id) {
		User user = userRepository.findById(id);
		user.setActive(1);
		userRepository.save(user);
	}

	@Override
	public void disableUser(int id) {
		User user = userRepository.findById(id);
		user.setActive(2);
		userRepository.save(user);
	}

}
