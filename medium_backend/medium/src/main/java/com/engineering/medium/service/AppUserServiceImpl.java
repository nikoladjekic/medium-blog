package com.engineering.medium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineering.medium.dao.AppUserDAO;
import com.engineering.medium.entity.AppUser;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserDAO appUserDAO;
	
	@Override
	@Transactional
	public List<AppUser> getAllAppUsers() {
		return appUserDAO.getAllAppUsers();
	}

	@Override
	@Transactional
	public AppUser getAppUser(String appUserUsername) {
		return appUserDAO.getAppUser(appUserUsername);
	}

	@Override
	@Transactional
	public void deleteAppUser(String appUserUsername) {
		appUserDAO.deleteAppUser(appUserUsername);
	}

	@Override
	@Transactional
	public void saveAppUser(AppUser appUser) {
		appUserDAO.saveAppUser(appUser);
	}

}
