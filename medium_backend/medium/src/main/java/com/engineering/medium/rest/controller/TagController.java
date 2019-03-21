package com.engineering.medium.rest.controller;

import java.util.List;

import javax.persistence.NoResultException;

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

import com.engineering.medium.entity.Tag;
import com.engineering.medium.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	TagService tagService;
	
	// ------------------- List all tags --------------------------------------------------

		@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Tag>> getAllTags() {
			List<Tag> tags = tagService.getAllTags();
			return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
		}
		
		
		// ------------------- Create new tag --------------------------------------------------

		@PostMapping(value = "/")
		public ResponseEntity<Void> createTag(@RequestBody Tag tag, UriComponentsBuilder ucBuilder) {
			HttpHeaders headers = new HttpHeaders();
			if(tagExists(tag.getTag())) {
			tagService.createTag(tag);
			headers.setLocation(ucBuilder.path("/tag/{id}").buildAndExpand(tag.getTagId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			}
			return new ResponseEntity<Void>(headers, HttpStatus.CONFLICT);

		}
		
		  //------------------- Delete tag --------------------------------------------------------
	     
	    @DeleteMapping(value = "/{tagId}")
	    public ResponseEntity<Tag> deleteTag(@PathVariable("tagId") long tagId) {
	        Tag tag = tagService.getTag(tagId);
	        if (tag == null) {
	            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);
	        }
	        tagService.deleteTag(tagId);
	        return new ResponseEntity<Tag>(HttpStatus.OK);
	    }
	    
	 // Method that checks does tag exists
		public boolean tagExists(String tag) {
			try {
				@SuppressWarnings("unused")
				Tag tempTag = tagService.getTagByName(tag);
			}
			catch(NoResultException ex) {
				return true;
			}
				return false;
			}

}
