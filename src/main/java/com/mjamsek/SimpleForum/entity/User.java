package com.mjamsek.SimpleForum.entity;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "display_name")
	private String displayName;
	
	@Column(name = "password")
	private String password;
	
	//0 - unconfirmed, 1 - active, 2 - unactive
	@Column(name = "active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	public String getStatusFromActive() {
		switch(active) {
		case 0 : 
			return "Unconfirmed";
		case 1:
			return "Active";
		case 2:
			return "Disabled";
		default : 
			return "No status";
		}
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public boolean hasRole(String search_role) {
		Iterator<Role> it = this.roles.iterator();
		while(it.hasNext()) {
			Role role = it.next();
			if(role.getRole().equals(search_role)) {
				return true;
			}
		}
		return false;
	}
	
	public String vrniRole() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(this.roles != null) {
			boolean prvi = true;
			Iterator<Role> it = this.roles.iterator();
			while(it.hasNext()) {
				Role role = it.next();
				if(prvi) {
					sb.append(role.getRole());
					prvi = false;
				} else {
					sb.append(", ");
					sb.append(role.getRole());
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	public String translateNepotrjenih(int st) {
		switch(st) {
			case 1:
				return "nepotrjen";
			case 2:
				return "nepotrjena";
			case 3:
			case 4:
				return "nepotrjeni";
			default :
				return "nepotrjenih";
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(this.roles != null) {
			boolean prvi = true;
			Iterator<Role> it = this.roles.iterator();
			while(it.hasNext()) {
				Role role = it.next();
				if(prvi) {
					sb.append(role.getRole());
					prvi = false;
				} else {
					sb.append(", ");
					sb.append(role.getRole());
				}
			}
		}
		sb.append("]");
		String roles = sb.toString();
		return String.format("{ id : %d, username : %s, displayName: %s, password : %s, active: %d, roles: %s }", this.id, this.username, this.displayName, this.password, this.active, roles);
	}
	
}
