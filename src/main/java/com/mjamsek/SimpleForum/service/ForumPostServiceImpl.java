package com.mjamsek.SimpleForum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjamsek.SimpleForum.entity.ForumPost;
import com.mjamsek.SimpleForum.repository.ForumPostRepository;

@Service("forumPostService")
public class ForumPostServiceImpl implements ForumPostService {

	@Autowired
	private ForumPostRepository forumPostRepository;
	
	@Override
	public List<ForumPost> vrniVsePoste() {
		List<ForumPost> seznam = forumPostRepository.findAll();
		return seznam;
	}

	@Override
	public void saveForumPost(ForumPost forumPost) {
		forumPostRepository.save(forumPost);
	}

	@Override
	public List<ForumPost> isciPoste(String query) {
		return forumPostRepository.isciPosteByQuery(query);
	}

}
