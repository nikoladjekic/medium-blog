package com.engineering.medium.rest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.engineering.medium.entity.AppUserDetails;
import com.engineering.medium.service.AppUserDetailsService;
import com.engineering.medium.service.AppUserService;

@RestController
public class RestLoginController {

	@Autowired
	AppUserService appUserService;
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@PostMapping(value="/loginUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUserDetails> getUser(@RequestBody AppUserDetails appUserDetails){
		AppUserDetails currentAppUser=appUserDetailsService.getUserDetailsByUsername(appUserDetails.getUsersUsername());;
		if(currentAppUser==null) {
			 return new ResponseEntity<AppUserDetails>(HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity<AppUserDetails>(currentAppUser, HttpStatus.OK);
	}
	
	@GetMapping(value = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpSession session) {
        session.invalidate();
    }
	
	
	
}
