package com.engineering.medium.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.engineering.medium.entity.AppUser;

@Repository
public class AppUserDAOImpl implements AppUserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<AppUser> getAllAppUsers() {
	
		Session currentSession = sessionFactory.getCurrentSession();
		Query <AppUser> query = currentSession.createQuery("from AppUser order by username", AppUser.class);
		List<AppUser> appUsers = query.getResultList();

		return appUsers;
	}

	@Override
	public AppUser getAppUser(String appUserUsername) {
		Session currentSession = sessionFactory.getCurrentSession();
		AppUser appUser = currentSession.get(AppUser.class, appUserUsername);
		return appUser;
	}

	@Override
	public void deleteAppUser(String appUserUsername) {
		Session currentSession = sessionFactory.getCurrentSession();
		AppUser appUser = currentSession.get(AppUser.class, appUserUsername);
		appUser.setEnabled(false);
		currentSession.saveOrUpdate(appUser);
	}

	@Override
	public void saveAppUser(AppUser appUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(appUser);
	}

}
