package com.engineering.medium.service;

import java.util.List;

import com.engineering.medium.entity.AppUser;

public interface AppUserService {

public List<AppUser> getAllAppUsers();
	
	public AppUser getAppUser(String appUserUsername);
	
	public void deleteAppUser(String appUserUsername);
	
	public void saveAppUser(AppUser appUser);
	
}
