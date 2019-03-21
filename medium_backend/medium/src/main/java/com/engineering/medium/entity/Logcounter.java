package com.engineering.medium.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the LOGCOUNTER database table.
 * 
 */
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "logcounterid")
@Entity
@Table(name="LOGCOUNTER")
@NamedQuery(name="Logcounter.findAll", query="SELECT l FROM Logcounter l")
public class Logcounter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "logcounter_id")
    @SequenceGenerator(name = "logcounter_id", sequenceName = "logcounter_id", allocationSize=1)
	private long logcounterid;

	@Column(name="\"date\"")
	private Date date;

	private String ipadress;

	//bi-directional one-to-one association to Blog
	@ManyToOne
	@JoinColumn(name="BLOG_BLOG_ID")
	private Blog blog;

	public Logcounter() {
	}

	public long getLogcounterid() {
		return this.logcounterid;
	}

	public void setLogcounterid(long logcounterid) {
		this.logcounterid = logcounterid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIpadress() {
		return this.ipadress;
	}

	public void setIpadress(String ipadress) {
		this.ipadress = ipadress;
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

}