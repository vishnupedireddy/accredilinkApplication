package com.accredilink.bgv.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.dto.ResponseDTO;
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
	public ResponseDTO createEmployee(AccrediEmployee employee) {
		
		/*
		 * Checking email id is valid or not, if it is invalid then throwing exception.
		 */
		boolean isValid = isEmailValid(employee.getEmailId());
		if (!isValid) {
			throw new AccredilinkException(Constants.INVALID_EMAIL_ID);
		}
		
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			employeeRepository.save(employee);
		} catch(Exception e) {
			logger.error("Exception raised in creating employee ", e);
			throw new AccredilinkException("Exception raised in creating employee ");
		}
		responseDTO.setMessage("Success");
		responseDTO.setStatudCode(HttpStatus.CREATED.value());
		
		return responseDTO;
	}

	@Transactional
	public ResponseDTO deleteEmployee(String ssnNumber) {
		Optional<AccrediEmployee> optionalEmployee = employeeRepository.findBySsnNumber(ssnNumber);
		if(!optionalEmployee.isPresent()) {
			throw new AccredilinkException("Employee not found with " + ssnNumber);
		}
		AccrediEmployee employee = optionalEmployee.get();
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			employeeRepository.delete(employee);
		} catch(Exception e) {
			logger.error("Exception raised in deleting employee ", e);
			throw new AccredilinkException("Exception raised in deleting employee ");
		}
		responseDTO.setMessage("Deleted employee successfully");
		responseDTO.setStatudCode(HttpStatus.OK.value());
		return responseDTO;
	}
	
	private boolean isEmailValid(String emailId) {
		EmailValidator emailValidator = new EmailValidator();
		return emailValidator.validate(emailId);
	}

}
