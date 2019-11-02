package com.accredilink.bgv.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.entity.Login;
import com.accredilink.bgv.entity.Registration;
import com.accredilink.bgv.exception.CustomException;
import com.accredilink.bgv.repository.LoginRepository;
import com.accredilink.bgv.repository.RegistrationRepository;
import com.accredilink.bgv.util.Constants;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	
	@Autowired
	NotificationService notificationService;
	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	LoginRepository loginRepository;

	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String registration(RegistrationDTO registrationDTO) throws Exception {
		
		/*
		 * Checking email id is valid or not, if it is invalid then throwing exception.
		 */
		boolean isValid = isEmailValid(registrationDTO.getEmailId());
		if(!isValid) {
			throw new CustomException(Constants.INVALID_EMAIL_ID);
		}
		
		/*
		 * Checking email id already exist or not, if it is exists then throwing exception.
		 */
		Optional<Registration> optionalRegistration = registrationRepository.findByEmailId(registrationDTO.getEmailId());
		if(optionalRegistration.isPresent()) {
			throw new CustomException(Constants.ALREADY_EMAIL_ID_REGISTERED);
		}
		
		Registration registration = new Registration();
		BeanUtils.copyProperties(registration, registrationDTO);
		registration.setCreationTimeStamp(LocalDateTime.now());
		
		try {
			registrationRepository.save(registration);
			logger.info("Registration is success with email id : " + registrationDTO.getEmailId());
			
			/* Inserting record into Login table */
			Login login = new Login();
			login.setUserName(registrationDTO.getEmailId());
			login.setPassword(registrationDTO.getPassword());
			loginRepository.save(login);
			
		} catch(Exception ex) {
			logger.error("Exception raised in user registration " + ex);
			return "Exception raised in registration";
		}
		
		boolean flag = notificationService.registrationNotification(registrationDTO);
		if(flag) {
			logger.info("Successfully sent email notification after registration.");
		} else {
			logger.error("ERROR : while sending notification at registration");
		}
		
		return "Registration is success";
	}

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	@Transactional
	public String login(String userName, String password) {
		
		Optional<Login> optionalLogin = loginRepository.findByUserNameAndPassword(userName, password);
		if(optionalLogin.isPresent()) {
			return Constants.LOGIN_SUCCESS;
		}
		return Constants.INVALID_CREDENTIALS;
	}
	
	private boolean isEmailValid(String emailId) {
		return true;
	}

}
