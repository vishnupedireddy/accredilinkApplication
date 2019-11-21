package com.accredilink.bgv.service;

import com.accredilink.bgv.dto.ResponseDTO;
import com.accredilink.bgv.entity.AccrediEmployee;

public interface EmployeeService {
	
	public ResponseDTO createEmployee(AccrediEmployee employee);
		
	public ResponseDTO deleteEmployee(String ssnNumber);
	
	

}
