package com.infosys.emailapi.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infosys.emailapi.constants.Constant;

@Entity
@Table(name=Constant.USER_TABLE_NAME)
public class User {
	
	@Id
	@Column(name=Constant.USER_COLUMN_USERNAME_NAME)
	private String username;
	
	@Column(name=Constant.USER_COLUMN_PASSWORD_NAME)
	private String password;
	
	@Column(name=Constant.USER_COLUMN_ACTIVE_NAME)
	private boolean active;
	
	public User() {}

	public User(String username, String password, boolean active) {
		this.username = username;
		this.password = password;
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isActive() {
		return active;
	}
	
	public String getActiveStatus( ) {
		if(active) {
			return Constant.YES;
		} else {
			return Constant.NO;
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
