package com.engineering.medium.dao;

import java.util.List;

import com.engineering.medium.entity.Tag;

public interface TagDAO {

	public List<Tag> getAllTags();
	
	public Tag getTag(long tagId);
	
	public Tag getTagByName(String tag);
	
	public List<Tag> getTagsBasedOnBlog(long blogId);
	
	public void createTag (Tag tag);
	
	public void deleteTag(long tagId);
	
		
}
