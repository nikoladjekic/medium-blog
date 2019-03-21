package com.engineering.medium.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.engineering.medium.entity.AppUser;
import com.engineering.medium.entity.AppUserDetails;
import com.engineering.medium.entity.UserAndDetails;
import com.engineering.medium.service.AppUserDetailsService;

@RestController
@RequestMapping("/register")
public class RegistrationController {

	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
		
	@PostMapping(value="/user", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> processRegistrationForm(@RequestBody UserAndDetails userAndDetails, RedirectAttributes redirectAttributes) 
		{
		
		AppUser appUser = new AppUser();
		appUser.setUserName(userAndDetails.getUsername());
		appUser.setPassword(userAndDetails.getPassword());
		// check the database if user already exists
		boolean userExists = doesUserExist(appUser.getUserName());
			
		if (userExists) {
			return ResponseEntity.badRequest().body("Blogger with this username already exists!");
		}
		
				
		// encrypt the password
        String encodedPassword = passwordEncoder.encode(appUser.getPassword());

        // prepend the encoding algorithm id
        encodedPassword = "{bcrypt}" + encodedPassword;
        
        
		// give user default role of "employee"
        List<GrantedAuthority> grantedAuthority = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");

        // create user object (from Spring Security framework)
        User tempUser = new User(appUser.getUserName(), encodedPassword, grantedAuthority);

        // save user in the database
        userDetailsManager.createUser(tempUser);
        
		AppUserDetails appUserDetails = new AppUserDetails();
		appUserDetails.setUsersUsername(userAndDetails.getUsername());
		appUserDetails.setFirstName(userAndDetails.getFirstName());
		appUserDetails.setLastName(userAndDetails.getLastName());
		appUserDetails.setEmail(userAndDetails.getEmail());
		appUserDetails.setRole("EMPLOYEE");
		appUserDetailsService.saveAppUserDetails(appUserDetails);

        		
        return ResponseEntity.ok().body("You have successfully registered new blogger!");
        
	}
	
	private boolean doesUserExist(String userName) {
		
		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);
		
		return exists;
	}

	
}
