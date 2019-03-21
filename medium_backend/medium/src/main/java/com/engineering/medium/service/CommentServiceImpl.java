package com.engineering.medium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineering.medium.dao.CommentDAO;
import com.engineering.medium.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDAO commentDAO;
	
	@Override
	@Transactional
	public List<Comment> getAllComments() {
		return commentDAO.getAllComments();
	}

	@Override
	@Transactional
	public Comment getComment(long commentId) {
		return commentDAO.getComment(commentId);
	}

	@Override
	@Transactional
	public void saveComment(Comment comment) {
		commentDAO.saveComment(comment);
	}

	@Override
	@Transactional
	public void deleteComment(long commentId) {
		commentDAO.deleteComment(commentId);
	}

	@Override
	@Transactional
	public void approveComment(long commentId) {
		commentDAO.approveComment(commentId);
	}

	@Override
	@Transactional
	public List<Comment> getCommentsByBlog(long blogId) {
		return commentDAO.getCommentsByBlog(blogId);
	}

	@Override
	@Transactional
	public List<Comment> getApprovedCommentsByBlog(long blogId) {
		return commentDAO.getApprovedCommentsByBlog(blogId);
	}

	@Override
	@Transactional
	public List<Comment> getUnapprovedCommentsByBlog(long blogId) {
		return commentDAO.getUnapprovedCommentsByBlog(blogId);
	}

	@Override
	@Transactional
	public List<Comment> getAllUnapprovedComments() {
		return commentDAO.getAllUnapprovedComments();
	}

}
