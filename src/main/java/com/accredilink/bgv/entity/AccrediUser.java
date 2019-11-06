package com.accredilink.bgv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCREDI_USER")
public class AccrediUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1263781284910412149L;

	@Id
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "USER_ROLE")
	private String userRole;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "PHONE_NUMBER")
	private Long phoneNo;
	
	@Column(name = "EMAIL_ID")
	private String emailId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
