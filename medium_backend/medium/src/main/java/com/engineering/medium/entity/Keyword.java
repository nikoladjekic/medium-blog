package com.engineering.medium.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


/**
 * The persistent class for the KEYWORD database table.
 * 
 */
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "keywordId")
@Entity
@Table(name="KEYWORD")
@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keyword_id")
    @SequenceGenerator(name = "keyword_id", sequenceName = "keyword_id", allocationSize=1)
	@Column(name="KEYWORD_ID")
	private long keywordId;

	private String word;

	//bi-directional many-to-one association to BlogKeyword
	@ManyToMany
	@JoinTable(name="BLOG_KEYWORDS", joinColumns= {@JoinColumn(name="KEYWORD_KEYWORD_ID")}, inverseJoinColumns= {@JoinColumn(name="BLOG_BLOG_ID")})
	private List<Blog> blogs;

	public Keyword() {
	}

	public long getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(long keywordId) {
		this.keywordId = keywordId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	

}