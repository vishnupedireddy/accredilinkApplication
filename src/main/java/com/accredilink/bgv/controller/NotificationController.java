package com.accredilink.bgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accredilink.bgv.dto.ResponseDTO;
import com.accredilink.bgv.service.NotificationService;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/notifyResetPassword")
	public ResponseDTO notifyForResetPassword(@RequestParam(required = true) String emailId) {
		boolean flag = notificationService.resetPasswordNotification(emailId);
		ResponseDTO respose = new ResponseDTO();
		if(flag) {
			respose.setMessage("Notification sent successfully");
			respose.setStatudCode(HttpStatus.OK.value());
		}
		respose.setMessage("Failed to sent notification");
		respose.setStatudCode(HttpStatus.NOT_FOUND.value());
		return respose;
	}
}
