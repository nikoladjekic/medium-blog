package com.engineering.medium.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.engineering.medium.entity.Keyword;

@Repository
public class KeywordDAOImpl implements KeywordDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Keyword> getAllKeywords() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Keyword> query = currentSession.createQuery("from Keyword order by word", Keyword.class);
		List<Keyword> keywords = query.getResultList();
		return keywords;
	}

	@Override
	public Keyword getKeyword(long keywordId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Keyword> query = currentSession.createQuery("from Keyword where keywordId=:keywordId", Keyword.class);
		query.setParameter("keywordId", keywordId);
		return query.getSingleResult();	}

	@Override
	public void saveKeyword(Keyword keyword) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(keyword);
	}

	@Override
	public void deleteKeyword(long keywordId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete from Keyword where keywordId=:keywordId");
		query.setParameter("keywordId", keywordId);
		query.executeUpdate();
	}

	@Override
	public Keyword getKeywordByName(String word) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Keyword> query = currentSession.createQuery("select k from Keyword k where k.word=:word", Keyword.class);
		query.setParameter("word", word);
		return query.getSingleResult();
	}

	@Override
	public List<Keyword> getKeywordsBasedOnBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Keyword> query = currentSession.createQuery("select k from Keyword k join k.blogs kb where kb.blogId=:blogId", Keyword.class);
		query.setParameter("blogId", blogId);
		return query.getResultList();
	}

}
