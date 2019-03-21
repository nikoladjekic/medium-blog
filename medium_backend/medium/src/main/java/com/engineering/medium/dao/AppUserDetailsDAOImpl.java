package com.engineering.medium.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.engineering.medium.entity.AppUser;
import com.engineering.medium.entity.AppUserDetails;

@Repository
public class AppUserDetailsDAOImpl implements AppUserDetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AppUserDetails> getAllAppUserDetails() {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<AppUserDetails> query = currentSession.createQuery("select a from AppUserDetails a where a.appUser.enabled=true order by firstName", AppUserDetails.class);
		List<AppUserDetails> appUserDetails = query.getResultList();

		return appUserDetails;
	}

	@Override
	public AppUserDetails getUserDetails(AppUser appUser) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<AppUserDetails> query = currentSession.createQuery("select a from AppUserDetails a where a.appUser=:appUser", AppUserDetails.class);
		query.setParameter("appUser", appUser);
		AppUserDetails appUserDetails = query.getSingleResult();
		return appUserDetails;
	}

	@Override
	public void saveAppUserDetails(AppUserDetails appUserDetails) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(appUserDetails);
	}


	@Override
	public boolean doesUserEmailExists(String email) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<String> query = currentSession.createQuery("select email from AppUserDetails", String.class);
		List<String> emails = query.getResultList();
		email.toLowerCase();
		if (emails.contains(email)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AppUserDetails getUserDetailsByUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<AppUserDetails> query = currentSession.createQuery("select a from AppUserDetails a where a.usersUsername =:username", AppUserDetails.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}

	@Override
	public void deleteUserDetails(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<?> query = currentSession.createQuery("delete from AppUserDetails where usersUsername=:username");
		query.setParameter("username", username);
		query.executeUpdate();
	}

}
