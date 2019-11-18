package com.accredilink.bgv.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accredilink.bgv.service.NotificationService;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/notifyResetPassword")
	public Map<String, String> notifyForResetPassword(@RequestParam(required = true) String emailId) {
		boolean flag = notificationService.resetPasswordNotification(emailId);
		Map<String, String> map = new HashMap<String, String>();
		if(flag) {
			map.put(emailId, "Notification sent successfully");
		}
		map.put(emailId, "Failed to send notification");
		return map;
	}
}
