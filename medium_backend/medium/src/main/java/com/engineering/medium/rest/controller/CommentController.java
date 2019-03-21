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

import com.engineering.medium.entity.Comment;
import com.engineering.medium.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	// ------------------- List all comments --------------------------------------------------
		
		@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Comment>> getAllComments() {
			List<Comment> comments = commentService.getAllComments();
			return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		}
		
		
		// ------------------- Create new comment --------------------------------------------------

		@PostMapping(value = "/")
		public ResponseEntity<String> createComment(@RequestBody Comment comment, UriComponentsBuilder ucBuilder) {
			HttpHeaders headers = new HttpHeaders();
			if (comment.getCommentator() == null || comment.getCommentator()=="") { 
				comment.setCommentator("anonymous");
				}
			commentService.saveComment(comment);
			headers.setLocation(ucBuilder.path("/comment/{id}").buildAndExpand(comment.getCommentId()).toUri());
			return ResponseEntity.status(HttpStatus.CREATED).body("Your comment has been submitted. Please wait for admin to approve it.");
		}
		
		
		// ------------------- Get approved comments by blog --------------------------------------------------

		@GetMapping(value = "/blog/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Comment>> getCommentsForBlog(@PathVariable("id") long blogId) {
			List <Comment> comments = commentService.getApprovedCommentsByBlog(blogId);
			if (comments.isEmpty()) {
				return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		}
		
		// ------------------- Get comment by id --------------------------------------------------

				@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<Comment> getCommentById(@PathVariable("id") long commentId) {
					Comment comment = commentService.getComment(commentId);
					if (comment == null) {
						return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<Comment>(comment, HttpStatus.OK);
				}

		
		// ------------------- Get unapproved comments by blog --------------------------------------------------

		@GetMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Comment>> getUnapprovedCommentsForBlog(@PathVariable("id") long blogId) {
			List <Comment> comments = commentService.getUnapprovedCommentsByBlog(blogId);
			if (comments.isEmpty()) {
				return new ResponseEntity<List<Comment>>(HttpStatus.CONTINUE);
			}
			return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		}	
		
		// ------------------- Get all unapproved comments --------------------------------------------------

				@GetMapping(value = "/admin/", produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<List<Comment>> getAllUnapprovedComments() {
					List <Comment> comments = commentService.getAllUnapprovedComments();
					if (comments.isEmpty()) {
						return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
					}
					return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
				}		

		//------------------- Delete a comment --------------------------------------------------------
	     
	    @DeleteMapping(value = "/admin/{id}")
	    public ResponseEntity<Comment> deleteComment(@PathVariable("id") long commentId) {
	        Comment comment = commentService.getComment(commentId);
	        if (comment == null) {
	            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
	        }
	        commentService.deleteComment(commentId);
	        return new ResponseEntity<Comment>(HttpStatus.OK);
	    }	
	    
		//------------------- Approve comment --------------------------------------------------------
	     
	    @GetMapping(value = "/admin/approve/{id}")
	    public ResponseEntity<Comment> approveComment(@PathVariable("id") long commentId) {
	        Comment comment = commentService.getComment(commentId);
	        if (comment == null) {
	            return new ResponseEntity<Comment>(comment, HttpStatus.NOT_FOUND);
	        }
	        commentService.approveComment(commentId);
	        //return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	        return ResponseEntity.ok().body(comment);
	    }
	    
	  
	
}
