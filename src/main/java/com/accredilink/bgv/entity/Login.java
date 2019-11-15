package com.accredilink.bgv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "LOGIN")
public class Login implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7182790261644460715L;

	@Id
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "CONFIRM_PASSWORD")
	private String confirmPassword;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
