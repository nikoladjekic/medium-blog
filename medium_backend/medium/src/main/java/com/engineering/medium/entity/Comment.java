package com.engineering.medium.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;


/**
 * The persistent class for the COMMENTS database table.
 * 
 */
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "commentId")
@Entity
@Table(name="COMMENTS")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id")
    @SequenceGenerator(name = "comment_id", sequenceName = "comment_id", allocationSize=1)
	@Column(name="COMMENT_ID")
	private long commentId;

	private boolean approved;

	@Column(name="COMMENTATOR")
	private String commentator;

	@Temporal(TemporalType.DATE)
	@Column(name="\"date\"")
	private Date date;

	@Lob
	private String text;

	//bi-directional many-to-one association to Blog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BLOG_BLOG_ID")
	private Blog blog;

	public Comment() {
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}


	public String getCommentator() {
		return commentator;
	}

	public void setCommentator(String commentator) {
		this.commentator = commentator;
	}

		
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}


}