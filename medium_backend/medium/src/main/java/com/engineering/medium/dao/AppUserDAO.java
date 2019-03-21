package com.engineering.medium.dao;

import java.util.List;

import com.engineering.medium.entity.AppUser;

public interface AppUserDAO {

	public List<AppUser> getAllAppUsers();
	
	public AppUser getAppUser(String appUserUsername);
	
	public void deleteAppUser(String appUserUsername);
	
	public void saveAppUser(AppUser appUser);
	
}
