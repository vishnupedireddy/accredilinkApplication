package com.accredilink.bgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.service.NotificationService;
import com.accredilink.bgv.service.RegistrationService;


@RestController
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	NotificationService nService;
	
	@PostMapping("/register")
	public String registration(@RequestBody RegistrationDTO registrationDTO) throws Exception {
		return registrationService.registration(registrationDTO);
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(required = true) String userName, @RequestParam(required = true) String password) {
		return registrationService.login(userName, password);
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam(required = true) String emailId, @RequestParam(required = true) String password,
			@RequestParam(required = true) String confirmPassword) {
		return registrationService.resetPassword(emailId, password, confirmPassword);
	}
}
