package com.engineering.medium.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.engineering.medium.entity.Blog;
import com.engineering.medium.service.BlogService;
@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	BlogService blogService;

	// ------------------- List all blogs --------------------------------------------------

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>> getAllBlogs() {
		List<Blog> blogs = blogService.getAllBlogs();
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	// ------------------- Get blog by id --------------------------------------------------

	@GetMapping(value = "/{blogId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Blog> getBlogById(@PathVariable("blogId") Long blogId) {

		Blog blog = blogService.getBlog(blogId);
		if (blog == null) {
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	// ------------------- Create new blog --------------------------------------------------

	@PostMapping(value = "/")
	public ResponseEntity<Void> createBlog(@RequestBody Blog blog, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		blogService.saveBlog(blog);
		headers.setLocation(ucBuilder.path("/blog/{id}").buildAndExpand(blog.getBlogId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Get blogs by domain --------------------------------------------------

	@GetMapping(value = "/domain/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>> getBlogsForDomain(@PathVariable("id") long domainId) {

		List<Blog> blogs = blogService.getBlogsByDomain(domainId);
		if (blogs == null) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}

	// ------------------- Get blogs by user --------------------------------------------------

	@GetMapping(value = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>> getBlogsByUsername(@PathVariable("username") String username) {

		List<Blog> blogs = blogService.getBlogsByUser(username);
		if (blogs == null) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}

	// ------------------- Filter blogs by tag, keyword and title --------------------------------------------------

	@GetMapping(value = "/search/{criteria}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>> filterBlogsByCriteria(@PathVariable("criteria") String criteria) {

		List<Blog> blogs = blogService.filterBlogsBasedOnSearchCriteria(criteria);
		if (blogs == null) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	  //------------------- Delete a blog --------------------------------------------------------
    
    @DeleteMapping(value = "/{blogId}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable("blogId") long blogId) {
 
        Blog blog = blogService.getBlog(blogId);
        if (blog == null) {
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
 
        blogService.deleteBlog(blogId);
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }	

 // ------------------- Filter blogs by date --------------------------------------------------

 	@GetMapping(value = "/{startDate}/dates/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<List<Blog>> filterBlogsByDate(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {

 		List<Blog> blogs = blogService.filterBlogsByDate(startDate, endDate);
 		if (blogs == null) {
 			return new ResponseEntity<List<Blog>>(HttpStatus.NOT_FOUND);
 		}
 		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
 	}    
	

}
