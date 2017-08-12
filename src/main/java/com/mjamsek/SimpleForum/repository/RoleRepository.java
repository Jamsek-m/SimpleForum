package com.mjamsek.SimpleForum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mjamsek.SimpleForum.entity.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRole(String role);
	
}
