package com.accredilink.bgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.entity.Registration;
import com.accredilink.bgv.entity.User;
import com.accredilink.bgv.service.BGVService;

@RestController
public class BGVController {
	
	@Autowired
	BGVService bgvService;
	
	@GetMapping("/view")
	public User view(@RequestParam(name = "roleType") String roleType, @RequestParam(name = "userId") Long userId) {
		return bgvService.view(roleType,userId);
	}
	
	@PostMapping("/update")
	public Registration update(@RequestBody RegistrationDTO registrationDTO) throws Exception {
		return bgvService.update(registrationDTO);
	}
	
	@GetMapping("/delete/{ssnNumber}")
	public void delete(@PathVariable Long ssnNumber) {
		bgvService.delete(ssnNumber);
	}

}
