package com.accredilink.bgv;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest {
	
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		
		Emp emp = new Emp();
		EmpVO vo = new EmpVO(1,"reddy");
		
		BeanUtils.copyProperties(vo, emp);
		
		System.out.println(emp.getId() + " ........" + emp.getName());
		
		
	}
	
	
}

class Emp {
	
	private int id;
	
	private String name;

	public Emp() {
		super();
	}

	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class EmpVO {
	
	private int id;
	
	private String name;
	
	///private String address;

	public EmpVO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		//this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public String getAddress() { return address; }
	 * 
	 * public void setAddress(String address) { this.address = address; }
	 */
	
}
