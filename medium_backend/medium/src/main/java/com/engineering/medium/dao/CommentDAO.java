package com.engineering.medium.dao;

import java.util.List;

import com.engineering.medium.entity.Comment;

public interface CommentDAO {
	
	public List<Comment> getAllComments();
	
	public Comment getComment(long commentId);
	
	public void saveComment(Comment comment);
	
	public void deleteComment(long commentId);
	
	public void approveComment(long commentId);
	
	public List<Comment> getCommentsByBlog(long blogId);
	
	public List<Comment> getApprovedCommentsByBlog(long blogId);
	
	public List<Comment> getUnapprovedCommentsByBlog(long blogId);
	
	public List<Comment> getAllUnapprovedComments();


}
