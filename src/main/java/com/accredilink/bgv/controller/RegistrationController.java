package com.accredilink.bgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.service.NotificationService;
import com.accredilink.bgv.service.RegistrationService;

/**
 * @author Vishnur
 *
 */

@RestController
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@PostMapping("/register")
	public String registration(@RequestBody RegistrationDTO registrationDTO) throws Exception {
		return registrationService.registration(registrationDTO);
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String userName, @RequestParam String password) {
		return registrationService.login(userName, password);
	}
	

}
