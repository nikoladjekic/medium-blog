package com.engineering.medium.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.engineering.medium.entity.Tag;

@Repository
public class TagDAOImpl implements TagDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Tag> getAllTags() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Tag> query = currentSession.createQuery("from Tag order by tag", Tag.class);
		List<Tag> tags = query.getResultList();
		return tags;
	}

	@Override
	public Tag getTag(long tagId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Tag> query = currentSession.createQuery("from Tag where tagId=:tagId", Tag.class);
		query.setParameter("tagId", tagId);
		return query.getSingleResult();
	}

	@Override
	public List<Tag> getTagsBasedOnBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Tag> query = currentSession.createQuery("select t from Tag t join t.blogs tb where tb.blogId=:blogId", Tag.class);
		query.setParameter("blogId", blogId);
		return query.getResultList();
	}

	@Override
	public void createTag(Tag tag) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(tag);		
	}

	@Override
	public void deleteTag(long tagId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete from Tag where tagId=:tagId");
		query.setParameter("tagId", tagId);
		query.executeUpdate();
		
	}

	@Override
	public Tag getTagByName(String tag) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Tag> query = currentSession.createQuery("select t from Tag t where t.tag=:tag", Tag.class);
		query.setParameter("tag", tag);
		return query.getSingleResult();
	}

}
