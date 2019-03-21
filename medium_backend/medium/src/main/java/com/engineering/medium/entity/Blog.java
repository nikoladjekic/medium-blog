package com.engineering.medium.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the BLOG database table.
 * 
 */

@Entity
@Table(name="BLOG")
@NamedQuery(name="Blog.findAll", query="SELECT b FROM Blog b")
public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_id")
    @SequenceGenerator(name = "blog_id", sequenceName = "blog_id", allocationSize=1)
	@Column(name="BLOG_ID", table = "BLOG")
	private long blogId;

	@Temporal(TemporalType.DATE)
	@Column(name="\"date\"")
	private Date date;

	@Column(name="SUBTITLE")
	private String subtitle;

	@Column(name="TEXT")
	private String text;

	@Column(name="TITLE")
	private String title;
	
	@Column(name="PICTURE")
	private String picture;

	
	//bi-directional many-to-one association to Domain
	@ManyToOne
	@JoinColumn(name="DOMAIN_DOMAIN_ID")
	private Domain domain;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERS_USERNAME")
	private AppUserDetails userDetails;

	//bi-directional many-to-one association to BlogKeyword
	@ManyToMany
	@JoinTable(name="BLOG_KEYWORDS", joinColumns= {@JoinColumn(name="BLOG_BLOG_ID")}, inverseJoinColumns= {@JoinColumn(name="KEYWORD_KEYWORD_ID")})
	private List<Keyword> keywords;

	//bi-directional many-to-one association to BlogTag
	@ManyToMany
	@JoinTable(name="BLOG_TAGS", joinColumns= {@JoinColumn(name="BLOG_BLOG_ID")}, inverseJoinColumns= {@JoinColumn(name="TAG_TAG_ID")})
	private List<Tag> tags;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="blog")
	private List<Comment> comments;

	//bi-directional one-to-one association to Logcounter
	@OneToMany(mappedBy="blog")
	private List<Logcounter> logcounters;

	public Blog() {
	}

	public long getBlogId() {
		return blogId;
	}

	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	

	public AppUserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(AppUserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Logcounter> getLogcounters() {
		return logcounters;
	}

	public void setLogcounters(List<Logcounter> logcounters) {
		this.logcounters = logcounters;
	}






}