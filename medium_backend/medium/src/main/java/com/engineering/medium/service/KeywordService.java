package com.engineering.medium.service;

import java.util.List;

import com.engineering.medium.entity.Keyword;

public interface KeywordService {

public List<Keyword> getAllKeywords();
	
	public Keyword getKeyword(long keywordId);
	
	public void saveKeyword(Keyword keyword);
	
	public void deleteKeyword(long keywordId);
	
	public Keyword getKeywordByName(String word);
	
	public List<Keyword> getKeywordsBasedOnBlog(long blogId);
	
}
