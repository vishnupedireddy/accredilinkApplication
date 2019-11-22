package com.accredilink.bgv.service;

import java.util.Map;

import com.accredilink.bgv.dto.ResponseObject;
import com.accredilink.bgv.entity.AccrediEmployee;

public interface EmployeeService {
	
	public ResponseObject createEmployee(AccrediEmployee employee);
		
	public Map<String, Long> deleteEmployee(Long empId);
	
	

}
