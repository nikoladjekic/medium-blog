package com.engineering.medium.service;

import java.util.List;

import com.engineering.medium.entity.AppUser;
import com.engineering.medium.entity.AppUserDetails;

public interface AppUserDetailsService {

public List<AppUserDetails> getAllAppUserDetails();
	
	public AppUserDetails getUserDetails(AppUser appUser);
		
	public AppUserDetails getUserDetailsByUsername(String username);
	
	public void saveAppUserDetails(AppUserDetails appUserDetails);
		
	public void deleteUserDetails(String username);
	
	public boolean doesUserEmailExists(String email);
	
	
}
