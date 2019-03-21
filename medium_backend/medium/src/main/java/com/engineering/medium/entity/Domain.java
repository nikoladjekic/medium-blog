package com.engineering.medium.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the "DOMAIN" database table.
 * 
 */

@Entity
@Table(name="\"DOMAIN\"")
@NamedQuery(name="Domain.findAll", query="SELECT d FROM Domain d")
public class Domain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domain_id")
    @SequenceGenerator(name = "domain_id", sequenceName = "domain_id", allocationSize=1)
	@Column(name="DOMAIN_ID")
	private long domainId;

	@Column(name="NAME")
	private String name;
	
	@Column(name="PICTURE")
	private String picture;

	//bi-directional many-to-one association to Blog
	@JsonIgnore
	@OneToMany(mappedBy="domain")
	private List<Blog> blogs;

	public Domain() {
	}

	public long getDomainId() {
		return domainId;
	}

	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	
	
}