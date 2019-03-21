package com.engineering.medium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineering.medium.dao.AppUserDetailsDAO;
import com.engineering.medium.entity.AppUser;
import com.engineering.medium.entity.AppUserDetails;

@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService {

	@Autowired
	private AppUserDetailsDAO appUserDetailsDAO;
	
	@Override
	@Transactional
	public List<AppUserDetails> getAllAppUserDetails() {
		return appUserDetailsDAO.getAllAppUserDetails();
	}

	@Override
	@Transactional
	public AppUserDetails getUserDetails(AppUser appUser) {
		return appUserDetailsDAO.getUserDetails(appUser);
	}

	@Override
	@Transactional
	public void saveAppUserDetails(AppUserDetails appUserDetails) {
		appUserDetailsDAO.saveAppUserDetails(appUserDetails);
	}


	@Override
	@Transactional
	public boolean doesUserEmailExists(String email) {
		return appUserDetailsDAO.doesUserEmailExists(email);
	}

	@Override
	@Transactional
	public AppUserDetails getUserDetailsByUsername(String username) {
		return appUserDetailsDAO.getUserDetailsByUsername(username);
	}

	@Override
	@Transactional
	public void deleteUserDetails(String username) {
		appUserDetailsDAO.deleteUserDetails(username);
	}

}
