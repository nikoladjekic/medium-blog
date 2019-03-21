package com.engineering.medium.service;

import java.util.List;

import com.engineering.medium.entity.Blog;

public interface BlogService {

	public List<Blog> getAllBlogs();
	
	public Blog getBlog(long blogId);
	
	public List<Blog> getBlogsByUser(String username);
	
	public List<Blog> getBlogsByDomain(long domainId);
	
	public void saveBlog(Blog blog);
	
	public void deleteBlog(long blogId);
		
	public Blog getLatestFromDomain(long domainId);
	
	public List<Blog> filterBlogsBasedOnSearchCriteria(String criteria);
	
	public List<Blog> filterBlogsByDate(String startDate, String endDate);



	
}
