package com.mjamsek.SimpleForum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "novica")
public class ForumPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "novica_id")
	private int id;
	
	@Column(name="content", columnDefinition = "TEXT")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return String.format("{ id: %d, content: %s, author: %d }", this.id, this.content, this.author.getId());
	}

}
