package com.mjamsek.SimpleForum.controller.wrapper;

import java.util.List;

public class EditUserWrapper {
	
	private String username;
	private String displayName;
	private String password;
	private List<String> roles;
	private int id;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "{ username : " + username + ", displayName : " + displayName + ", password : " + password + ", roles : "
				+ roles + ", id : " + id + " }";
	}

}
