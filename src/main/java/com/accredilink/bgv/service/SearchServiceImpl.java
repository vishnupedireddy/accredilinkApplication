package com.accredilink.bgv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.entity.AccrediEmployee;
import com.accredilink.bgv.repository.EmployeeRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<AccrediEmployee> searchEmployee(String searchStr) {

		List<AccrediEmployee> employeeList = employeeRepository.findByFirstNameOrLastNameOrEmailIdOrSsnNumber(searchStr,
				searchStr, searchStr, searchStr);
		return employeeList;
	}
}
