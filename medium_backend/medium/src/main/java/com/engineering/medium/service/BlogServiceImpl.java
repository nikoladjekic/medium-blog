package com.engineering.medium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineering.medium.dao.BlogDAO;
import com.engineering.medium.entity.Blog;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogDAO blogDAO;

	@Override
	@Transactional
	public List<Blog> getAllBlogs() {
		return blogDAO.getAllBlogs();
	}

	@Override
	@Transactional
	public Blog getBlog(long blogId) {
		return blogDAO.getBlog(blogId);
	}

	@Override
	@Transactional
	public List<Blog> getBlogsByUser(String username) {
		return blogDAO.getBlogsByUser(username);
	}

	@Override
	@Transactional
	public List<Blog> getBlogsByDomain(long domainId) {
		return blogDAO.getBlogsByDomain(domainId);
	}

	@Override
	@Transactional
	public void saveBlog(Blog blog) {
		blogDAO.saveBlog(blog);
	}

	@Override
	@Transactional
	public void deleteBlog(long blogId) {
		blogDAO.deleteBlog(blogId);
	}

	@Override
	@Transactional
	public Blog getLatestFromDomain(long domainId) {
		return blogDAO.getLatestFromDomain(domainId);
	}

	@Override
	@Transactional
	public List<Blog> filterBlogsBasedOnSearchCriteria(String criteria) {
		return blogDAO.filterBlogsBasedOnSearchCriteria(criteria);
	}

	@Override
	@Transactional
	public List<Blog> filterBlogsByDate(String startDate, String endDate) {
		return blogDAO.filterBlogsByDate(startDate, endDate);
	}

}
