package com.engineering.medium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineering.medium.dao.KeywordDAO;
import com.engineering.medium.entity.Keyword;

@Service
public class KeywordServiceImpl implements KeywordService {
	
	@Autowired
	KeywordDAO keywordDAO;

	@Override
	@Transactional
	public List<Keyword> getAllKeywords() {
		return keywordDAO.getAllKeywords();
	}

	@Override
	@Transactional
	public Keyword getKeyword(long keywordId) {
		return keywordDAO.getKeyword(keywordId);
	}

	@Override
	@Transactional
	public void saveKeyword(Keyword keyword) {
		keywordDAO.saveKeyword(keyword);
	}

	@Override
	@Transactional
	public void deleteKeyword(long keywordId) {
		keywordDAO.deleteKeyword(keywordId);
	}

	@Override
	@Transactional
	public Keyword getKeywordByName(String word) {
		return keywordDAO.getKeywordByName(word);
	}

	@Override
	@Transactional
	public List<Keyword> getKeywordsBasedOnBlog(long blogId) {
		return keywordDAO.getKeywordsBasedOnBlog(blogId);
	}

}
