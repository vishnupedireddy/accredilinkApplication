package com.accredilink.bgv.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.dto.ResponseObject;
import com.accredilink.bgv.entity.AccrediEmployee;
import com.accredilink.bgv.service.EmployeeService;

@RestController("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseObject> createEmployee(@RequestBody AccrediEmployee employee){
		return new ResponseEntity<ResponseObject>(employeeService.createEmployee(employee), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<Map<String,Long>> deleteEmployee(@PathVariable Long empId){
		return new ResponseEntity<Map<String,Long>>(employeeService.deleteEmployee(empId), HttpStatus.OK);
	}
}
