package com.mjamsek.SimpleForum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mjamsek.SimpleForum.entity.ForumPost;

@Repository("forumPostRepository")
public interface ForumPostRepository  extends JpaRepository<ForumPost, Long>{

	@Query("select p from ForumPost p where p.content like %:param%")
	public List<ForumPost> isciPosteByQuery(@Param("param") String query);
	
}
