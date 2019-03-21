package com.engineering.medium.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.engineering.medium.entity.Blog;
import com.engineering.medium.entity.Domain;
import com.engineering.medium.service.BlogService;
import com.engineering.medium.service.DomainService;

@RestController
@RequestMapping("/domain")
public class DomainController {

	@Autowired
	DomainService domainService;

	@Autowired
	BlogService blogService;

	// ------------------- List all domains ------------------------

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Domain>> getAllDomains() {
		List<Domain> domains = domainService.getAllDomains();
		if (domains.isEmpty()) {
			return new ResponseEntity<List<Domain>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Domain>>(domains, HttpStatus.OK);
	}

	// ------------------- Create new domain ---------------------

	@PostMapping(value = "/")
	public ResponseEntity<Void> createDomain(@RequestBody Domain domain, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		if (domainExists(domain.getName())) {
			domainService.saveDomain(domain);
			headers.setLocation(ucBuilder.path("/domain/{id}").buildAndExpand(domain.getDomainId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(headers, HttpStatus.CONFLICT);
		}
	}

	// ------------------- Get latest blog from each domain -------------------

	@GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>> getLatestBlogFromEachDomain() {
		List<Domain> domains = domainService.getAllDomains();
		List<Blog> latestBlogs = new ArrayList<>();
		if (domains.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		for (Domain domain : domains) {
			Blog blog = blogService.getLatestFromDomain(domain.getDomainId());
			if (blog != null) {
				latestBlogs.add(blog);
			}
		}
		return new ResponseEntity<List<Blog>>(latestBlogs, HttpStatus.OK);
	}

	// ------------------- Get all blog from domain -----------------

	@GetMapping(value = "/blogs/{domainId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>> getAllBlogaFromDomain(@PathVariable long domainId) {
		Domain domain = domainService.getDomainById(domainId);
		List<Blog> blogs = domain.getBlogs();
		if (blogs.isEmpty()) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}

	// ------------------- Get domain by id -----------------------

	@GetMapping(value = "/{domainId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Domain> getDomain(@PathVariable long domainId) {
		Domain domain = domainService.getDomainById(domainId);
		if (domain == null) {
			return new ResponseEntity<Domain>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Domain>(domain, HttpStatus.OK);
	}

	// Method that checks does domain exists
	public boolean domainExists(String domainName) {
		try {
			@SuppressWarnings("unused")
			Domain domain = domainService.getDomainByName(domainName);
		} catch (NoResultException ex) {
			return true;
		}
		return false;
	}

}
