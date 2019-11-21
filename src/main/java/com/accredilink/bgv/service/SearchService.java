package com.accredilink.bgv.service;

import java.util.List;

import com.accredilink.bgv.entity.AccrediEmployee;

public interface SearchService {
	
	public List<AccrediEmployee> searchEmployee(String searchStr);

}
