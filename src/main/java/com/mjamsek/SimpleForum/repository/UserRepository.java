package com.mjamsek.SimpleForum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mjamsek.SimpleForum.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
	@Query("SELECT COUNT(*) as UNCONFIRMED FROM User WHERE active=0")
	public long countUnconfirmedNumber();
	
	@Query("SELECT u FROM User u WHERE u.active=0")
	public List<User> getAllUnconfirmedUsers();
	
	public User findById(int id);
	
}
