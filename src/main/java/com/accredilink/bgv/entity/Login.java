package com.accredilink.bgv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@Column(name = "ENTRY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long entryId;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "CONFIRM_PASSWORD")
	private String confirmPassword;

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public String getUserName() {
		return emailId;
	}

	public void setUserName(String userName) {
		this.emailId = userName;
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
