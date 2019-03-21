package com.engineering.medium.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;


import com.engineering.medium.entity.Logcounter;

@Repository
public class LogcounterDAOImpl implements LogcounterDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Logcounter> getAllLogs() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Logcounter> query = currentSession.createQuery("from Logcounter order by date", Logcounter.class);
		return query.getResultList();
	}

	@Override
	public List<Logcounter> getAllLogsForBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Logcounter> query = currentSession.createQuery("select l from Logcounter l where l.blog.blogId=:blogId", Logcounter.class);
		query.setParameter("blogId", blogId);
		return query.getResultList();
	}

	@Override
	public Long getNumberOfLogsForBlog(long blogId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Long> query = currentSession.createQuery("select count(*) from Logcounter l where l.blog.blogId=:blogId", Long.class);
		query.setParameter("blogId", blogId);
		return query.uniqueResult();
	}

	@Override
	public void saveLogCounter(Logcounter logcounter) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(logcounter);
	}

}
