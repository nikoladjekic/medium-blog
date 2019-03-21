package com.engineering.medium.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.engineering.medium.entity.Domain;

@Repository
public class DomainDAOImpl implements DomainDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveDomain(Domain domain) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(domain);
	}

	@Override
	public void deleteDomain(long domainId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete from Domain where domainId=:domainId");
		query.setParameter("domainId", domainId);
		query.executeUpdate();
	}

	@Override
	public List<Domain> getAllDomains() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Domain> query = currentSession.createQuery("from Domain order by name", Domain.class);
		return query.getResultList();
	}

	@Override
	public Domain getDomainById(long domainId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Domain domain = currentSession.get(Domain.class, domainId);
		Hibernate.initialize(domain.getBlogs());
		return domain;
	}

	@Override
	public Domain getDomainByName(String domainName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Domain> query = currentSession.createQuery("select d from Domain d where d.name=:domainName", Domain.class);
		query.setParameter("domainName", domainName);
		return query.getSingleResult();
	}

}
