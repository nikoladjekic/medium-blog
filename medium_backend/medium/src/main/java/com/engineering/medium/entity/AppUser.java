package com.engineering.medium.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class AppUser {

	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Id
	@Column(name="username")
	private String username;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private Boolean enabled;
			
	public AppUser() {
		
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	
}
