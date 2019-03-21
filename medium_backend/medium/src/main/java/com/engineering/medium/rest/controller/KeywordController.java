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

import com.engineering.medium.entity.Keyword;
import com.engineering.medium.service.KeywordService;

@RestController
@RequestMapping("/keyword")
public class KeywordController {

	@Autowired
	KeywordService keywordService;

	// ------------------- List all keywords -------------------------

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Keyword>> getAllKeywords() {
		List<Keyword> keywords = keywordService.getAllKeywords();
		return new ResponseEntity<List<Keyword>>(keywords, HttpStatus.OK);
	}

	// ------------------- Create new keyword -------------------------

	@PostMapping(value = "/")
	public ResponseEntity<Void> createKeyword(@RequestBody Keyword keyword, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		if (keywordExists(keyword.getWord())) {
			keywordService.saveKeyword(keyword);
			headers.setLocation(ucBuilder.path("/keyword/{id}").buildAndExpand(keyword.getKeywordId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(headers, HttpStatus.CONFLICT);
	}

	// ------------------- Delete keyword -------------------------

	@DeleteMapping(value = "/{keywordId}")
	public ResponseEntity<Keyword> deleteKeyword(@PathVariable("keywordId") long keywordId) {
		Keyword keyword = keywordService.getKeyword(keywordId);
		if (keyword == null) {
			return new ResponseEntity<Keyword>(HttpStatus.NOT_FOUND);
		}
		keywordService.deleteKeyword(keywordId);
		return new ResponseEntity<Keyword>(HttpStatus.OK);
	}

	// Method that checks does keyword exists
	public boolean keywordExists(String word) {
		try {
			@SuppressWarnings("unused")
			Keyword tempKeyword = keywordService.getKeywordByName(word);
		} catch (NoResultException ex) {
			return true;
		}
		return false;
	}

}
