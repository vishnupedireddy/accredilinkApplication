package com.accredilink.bgv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS")
public class Status implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3267786748189256676L;

	@Id
	@Column(name = "STATUS_CODE")
	private Long statusCode;
	
	@Column(name = "STATUS")
	private String status;
	
	public Long getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
