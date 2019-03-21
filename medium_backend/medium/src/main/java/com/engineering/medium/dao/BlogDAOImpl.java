package com.engineering.medium.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.engineering.medium.entity.Blog;

@Repository
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Blog> getAllBlogs() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Blog> query = currentSession.createQuery("from Blog order by date", Blog.class);
		List<Blog> blogs = query.getResultList();
		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getLogcounters());
		}
		return blogs;
	}

	@Override
	public Blog getBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Blog blog = currentSession.get(Blog.class, blogId);
		Hibernate.initialize(blog.getLogcounters());
		return blog;
	}

	@Override
	public List<Blog> getBlogsByUser(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Blog> query = currentSession.createQuery("select b from Blog b where b.userDetails.usersUsername=:username order by date", Blog.class);
		query.setParameter("username", username);
		List<Blog> blogs = query.getResultList();
		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getLogcounters());
			Hibernate.initialize(blog.getTags());
		}
		return blogs;
	}

	@Override
	public List<Blog> getBlogsByDomain(long domainId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Blog> query = currentSession.createQuery("select b from Blog b where b.domain.domainId=:domainId order by date", Blog.class);
		query.setParameter("domainId", domainId);
		List<Blog> blogs = new ArrayList<>();
		try {
		blogs = query.getResultList();
		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getDomain());
			Hibernate.initialize(blog.getKeywords());
			Hibernate.initialize(blog.getTags());
			Hibernate.initialize(blog.getComments());
			Hibernate.initialize(blog.getUserDetails());
			Hibernate.initialize(blog.getLogcounters());

		}
		}
		catch (NoResultException ex){
		}
		return query.getResultList();
	}

	@Override
	public void saveBlog(Blog blog) {
		Session currentSession = sessionFactory.getCurrentSession();
		blog.setDate(new Date());
		currentSession.saveOrUpdate(blog);
	}

	@Override
	public void deleteBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete from Blog where blogId=:blogId");
		query.setParameter("blogId", blogId);
		query.executeUpdate();
	}

	@Override
	public Blog getLatestFromDomain(long domainId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Blog> query = currentSession.createQuery("select b from Blog b where b.domain.domainId=:domainId order by date desc", Blog.class);
		query.setParameter("domainId", domainId);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Blog blog = null;
		try {
		blog = query.getSingleResult();
		}
		catch(NoResultException ex) {
		}
		if(blog!=null) {
			Hibernate.initialize(blog.getTags());
			Hibernate.initialize(blog.getLogcounters());
		}
		return blog;
	}

	@Override
	public List<Blog> filterBlogsBasedOnSearchCriteria(String criteria) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Long> queryByKeywords = currentSession.createQuery("select b.blogId from Blog b join b.keywords bk where bk.keywordId in(select k.keywordId from Keyword k where lower(k.word) like :criteria)", Long.class);
		queryByKeywords.setParameter("criteria", '%'+criteria.toLowerCase()+'%');
		List<Long> blogsByKeyword = queryByKeywords.getResultList();
		Query<Long> queryByTag = currentSession.createQuery("select b.blogId from Blog b join b.tags bt where bt.tagId in(select t.tagId from Tag t where lower(t.tag) like :criteria)", Long.class);
		queryByTag.setParameter("criteria", '%'+criteria.toLowerCase()+'%');
		List<Long> blogsByTag = queryByTag.getResultList();
		Query<Long> queryByTitle = currentSession.createQuery("select b.blogId from Blog b where lower(b.title) like :criteria", Long.class);
		queryByTitle.setParameter("criteria", '%'+criteria.toLowerCase()+'%');
		List<Long> blogsByTitle = queryByTitle.getResultList();
		Set<Long> blogSet = new LinkedHashSet<>(blogsByKeyword);
		blogSet.addAll(blogsByTag);
		blogSet.addAll(blogsByTitle);
		List<Blog> blogs = new ArrayList<>();
		Query<Blog> queryBlogs = currentSession.createQuery("select b from Blog b where blogId=:blogId", Blog.class);
		for (Long tempBlog : blogSet) {
			queryBlogs.setParameter("blogId", tempBlog);
			blogs.add(queryBlogs.getSingleResult());
		}
		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getTags());
			Hibernate.initialize(blog.getLogcounters());
		}
		return blogs;
	}

	@Override
	public List<Blog> filterBlogsByDate(String startDate, String endDate) {
		Session currentSession = sessionFactory.getCurrentSession();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDateToDate = null;
		Date endDateToDate = null;
		try {
			startDateToDate = format.parse(startDate);
			endDateToDate = format.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Query<Blog> query = currentSession.createQuery("select b from Blog b where b.date between :startDate and :endDate", Blog.class);
		query.setParameter("startDate", startDateToDate);
		query.setParameter("endDate", endDateToDate);
		List<Blog> blogs = query.getResultList();
		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getLogcounters());
		}
		return blogs;
	}

}
