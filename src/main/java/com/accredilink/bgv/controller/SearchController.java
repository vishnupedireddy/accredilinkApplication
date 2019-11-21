package com.accredilink.bgv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.entity.AccrediEmployee;
import com.accredilink.bgv.service.SearchService;

@RestController
public class SearchController {
	
	@Autowired
	SearchService searchService;

	@GetMapping("/search/{searchStr}")
	public List<AccrediEmployee> search(@PathVariable String searchStr){
		return searchService.searchEmployee(searchStr);
	}
	

}
