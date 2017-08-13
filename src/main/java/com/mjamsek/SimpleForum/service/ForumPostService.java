package com.mjamsek.SimpleForum.service;

import java.util.List;

import com.mjamsek.SimpleForum.entity.ForumPost;

public interface ForumPostService {

	public List<ForumPost> vrniVsePoste();
	
	public void saveForumPost(ForumPost forumPost);
	
	public List<ForumPost> isciPoste(String query);
	
}
