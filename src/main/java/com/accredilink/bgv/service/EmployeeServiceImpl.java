package com.accredilink.bgv.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.dto.ResponseObject;
import com.accredilink.bgv.entity.AccrediEmployee;
import com.accredilink.bgv.exception.AccredilinkException;
import com.accredilink.bgv.repository.EmployeeRepository;
import com.accredilink.bgv.util.Constants;
import com.accredilink.bgv.util.EmailValidator;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional
	public ResponseObject createEmployee(AccrediEmployee employee) {
		
		/*
		 * Checking email id is valid or not, if it is invalid then throwing exception.
		 */
		boolean isValid = isEmailValid(employee.getEmailId());
		if (!isValid) {
			throw new AccredilinkException(Constants.INVALID_EMAIL_ID);
		}
		
		ResponseObject responseObject = new ResponseObject();
		try {
			employeeRepository.save(employee);
		} catch(Exception e) {
			logger.error("Exception raised in creating employee ", e);
			throw new AccredilinkException("Exception raised in creating employee ");
		}
		responseObject.setMessage("Success");
		responseObject.setStatudCode(1);
		return responseObject;
	}

	@Transactional
	public Map<String, Long> deleteEmployee(Long empId) {
		Optional<AccrediEmployee> optionalEmployee = employeeRepository.findById(empId);
		if(!optionalEmployee.isPresent()) {
			throw new AccredilinkException("Employee not found with " + empId);
		}
		AccrediEmployee employee = optionalEmployee.get();
		try {
			employeeRepository.delete(employee);
		} catch(Exception e) {
			logger.error("Exception raised in deleting employee ", e);
			throw new AccredilinkException("Exception raised in deleting employee ");
		}
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("EmployeeId", empId);
		return map;
	}
	
	private boolean isEmailValid(String emailId) {
		EmailValidator emailValidator = new EmailValidator();
		return emailValidator.validate(emailId);
	}

}
