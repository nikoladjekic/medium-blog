package com.engineering.medium.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


/**
 * The persistent class for the TAG database table.
 * 
 */
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "tagId")
@Entity
@Table(name="TAG")
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id")
    @SequenceGenerator(name = "tag_id", sequenceName = "tag_id", allocationSize=1)
	@Column(name="TAG_ID")
	private long tagId;

	private String tag;

	//bi-directional many-to-one association to BlogTag
	@ManyToMany
	@JoinTable(name="BLOG_TAGS", joinColumns= {@JoinColumn(name="TAG_TAG_ID")}, inverseJoinColumns= {@JoinColumn(name="BLOG_BLOG_ID")})
	private List<Blog> blogs;

	public Tag() {
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	
}