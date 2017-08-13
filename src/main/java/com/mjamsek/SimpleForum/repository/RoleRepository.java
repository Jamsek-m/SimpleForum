package com.mjamsek.SimpleForum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mjamsek.SimpleForum.entity.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRole(String role);
	
	public List<Role> findAll();
	
	public Role findById(int id);
	
}
