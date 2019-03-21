package com.engineering.medium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineering.medium.dao.TagDAO;
import com.engineering.medium.entity.Tag;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	TagDAO tagDAO;

	@Transactional
	@Override
	public List<Tag> getAllTags() {
		return tagDAO.getAllTags();
	}

	@Transactional
	@Override
	public Tag getTag(long tagId) {
		return tagDAO.getTag(tagId);
	}

	@Transactional
	@Override
	public List<Tag> getTagsBasedOnBlog(long blogId) {
		return tagDAO.getTagsBasedOnBlog(blogId);
	}

	@Transactional
	@Override
	public void createTag(Tag tag) {
		tagDAO.createTag(tag);
	}

	@Transactional
	@Override
	public void deleteTag(long tagId) {
		tagDAO.deleteTag(tagId);
	}

	@Transactional
	@Override
	public Tag getTagByName(String tag) {
		return tagDAO.getTagByName(tag);
	}

}
