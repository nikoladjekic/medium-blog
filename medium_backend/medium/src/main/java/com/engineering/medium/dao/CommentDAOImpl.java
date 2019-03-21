package com.engineering.medium.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.engineering.medium.entity.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Comment> getAllComments() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Comment> query = currentSession.createQuery("from Comment order by date", Comment.class);
		List<Comment> comments = query.getResultList();
		return comments;
	}

	@Override
	public Comment getComment(long commentId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Comment comment = currentSession.get(Comment.class, commentId);
		return comment;
	}

	@Override
	public void saveComment(Comment comment) {
		Session currentSession = sessionFactory.getCurrentSession();
		comment.setDate(new Date());
		currentSession.save(comment);
	}

	@Override
	public void deleteComment(long commentId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete from Comment where commentId=:commentId");
		query.setParameter("commentId", commentId);
		query.executeUpdate();
	}

	@Override
	public void approveComment(long commentId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Comment comment = currentSession.get(Comment.class, commentId);
		comment.setApproved(true);
		currentSession.saveOrUpdate(comment);
	}

	@Override
	public List<Comment> getCommentsByBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Comment>query = currentSession.createQuery("select c from Comment c where c.blog.blogId=:blogId order by date", Comment.class);
		query.setParameter("blogId", blogId);
		List<Comment> comments = query.getResultList();
		return comments;
	}

	@Override
	public List<Comment> getApprovedCommentsByBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Comment>query = currentSession.createQuery("select c from Comment c where c.blog.blogId=:blogId and c.approved=true order by date", Comment.class);
		query.setParameter("blogId", blogId);
		List<Comment> comments = query.getResultList();
		return comments;
	}

	@Override
	public List<Comment> getUnapprovedCommentsByBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Comment>query = currentSession.createQuery("select c from Comment c where c.blog.blogId=:blogId and c.approved=false order by date", Comment.class);
		query.setParameter("blogId", blogId);
		List<Comment> comments = query.getResultList();
		return comments;
	}

	@Override
	public List<Comment> getAllUnapprovedComments() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Comment>query = currentSession.createQuery("select c from Comment c where c.approved=false", Comment.class);
		List<Comment> comments = query.getResultList();
		for (Comment comment : comments) {
			Hibernate.initialize(comment.getBlog().getTitle());
		}
		return comments;
	}

}
