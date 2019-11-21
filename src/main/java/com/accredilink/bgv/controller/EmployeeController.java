package com.accredilink.bgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.dto.ResponseDTO;
import com.accredilink.bgv.entity.AccrediEmployee;
import com.accredilink.bgv.service.EmployeeService;

@RestController("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/create")
	public ResponseDTO createEmployee(@RequestBody AccrediEmployee employee){
		return employeeService.createEmployee(employee);
	}
	
	@DeleteMapping("/delete/{ssnNumber}")
	public ResponseDTO deleteEmployee(@PathVariable String ssnNumber){
		return employeeService.deleteEmployee(ssnNumber);
	}
}
