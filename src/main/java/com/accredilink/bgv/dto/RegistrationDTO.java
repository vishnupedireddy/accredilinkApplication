package com.accredilink.bgv.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class RegistrationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -683870101072295886L;
	
	private Long ssnNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String password;
	
	private LocalDate dateOfBirth;

	public Long getSsnNumber() {
		return ssnNumber;
	}

	public void setSsnNumber(Long ssnNumber) {
		this.ssnNumber = ssnNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
