package com.engineering.medium.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.engineering.medium.entity.AppUser;
import com.engineering.medium.entity.AppUserDetails;
import com.engineering.medium.service.AppUserDetailsService;
import com.engineering.medium.service.AppUserService;

@RestController
@RequestMapping("/user")
public class AppUserController {

	@Autowired
	AppUserService appUserService;

	@Autowired
	AppUserDetailsService appUserDetailsService;

	// ------------------- List all users -----------------------------------

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppUserDetails>> getUsers() {
		List<AppUserDetails> users = appUserDetailsService.getAllAppUserDetails();
		if (users.isEmpty()) {
			return new ResponseEntity<List<AppUserDetails>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AppUserDetails>>(users, HttpStatus.OK);
	}

	// ------------------- Get single user------------------------------------

	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUser> getUser(@PathVariable("username") String username) {
		AppUser appUser = appUserService.getAppUser(username);
		if (appUser == null) {
			return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
	}

	// ------------------- Get single users details----------------------------

	@GetMapping(value = "details/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUserDetails> getUserDetails(@PathVariable("username") String username) {
		AppUserDetails appUserDetails = appUserDetailsService.getUserDetailsByUsername(username);
		if (appUserDetails == null) {
			return new ResponseEntity<AppUserDetails>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AppUserDetails>(appUserDetails, HttpStatus.OK);
	}

	// ------------------- Update users details------------------------------

	@PutMapping(value = "details/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUserDetails(@RequestBody AppUserDetails appUserDetails,
			UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		appUserDetails.setRole("EMPLOYEE");
		appUserDetailsService.saveAppUserDetails(appUserDetails);
		
		headers.setLocation(
				ucBuilder.path("/user/details/{username}").buildAndExpand(appUserDetails.getUsersUsername()).toUri());
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("User has been successfully updated!");
	}

	// ------------------- Delete user ------------------------------------------

	@DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppUser> deleteUser(@PathVariable("username") String username) {
		AppUser appUser = appUserService.getAppUser(username);
		if (appUser == null) {
			return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
		}
		appUserService.deleteAppUser(username);
		return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
	}

}
