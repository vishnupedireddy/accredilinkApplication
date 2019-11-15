package com.accredilink.bgv.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.dto.ResponseDTO;
import com.accredilink.bgv.entity.Address;
import com.accredilink.bgv.entity.Company;
import com.accredilink.bgv.entity.Login;
import com.accredilink.bgv.entity.Registration;
import com.accredilink.bgv.entity.User;
import com.accredilink.bgv.entity.UserType;
import com.accredilink.bgv.exception.AccredilinkException;
import com.accredilink.bgv.repository.LoginRepository;
import com.accredilink.bgv.repository.RegistrationRepository;
import com.accredilink.bgv.repository.UserRepository;
import com.accredilink.bgv.util.Constants;
import com.accredilink.bgv.util.EmailValidator;
import com.accredilink.bgv.util.EncriptAndDescript;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	NotificationService notificationService;
	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	UserRepository userRepository;

	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ResponseDTO registration(RegistrationDTO registrationDTO) throws Exception {

		/*
		 * Checking email id is valid or not, if it is invalid then throwing exception.
		 */

		boolean isValid = isEmailValid(registrationDTO.getEmailId());
		if (!isValid) {
			throw new AccredilinkException(Constants.INVALID_EMAIL_ID);
		}

		/*
		 * Checking email id already exist or not, if it is exists then throwing
		 * exception.
		 */

		Optional<Registration> optionalRegistration = registrationRepository
				.findByEmailId(registrationDTO.getEmailId());
		if (optionalRegistration.isPresent()) {
			throw new AccredilinkException(Constants.ALREADY_EMAIL_ID_REGISTERED);
		}

		try {

			User user = new User();
			User saveUser = null;
			String encryptedPassword = EncriptAndDescript.encrypt(registrationDTO.getPassword());
			user.setUserName(registrationDTO.getUserName());
			user.setPassword(encryptedPassword);
			user.setFirstName(registrationDTO.getFirstName());
			user.setLastName(registrationDTO.getLastName());
			user.setDateOfBirth(registrationDTO.getDateOfBirth());
			user.setEmailId(registrationDTO.getEmailId());
			user.setSsnNumber(registrationDTO.getSsnNumber());
			user.setPhoneNumber(registrationDTO.getPhoneNo());

			/*
			 * Checking the User, If the User is Employee then inserting records into only
			 * User table
			 */

			if (registrationDTO.getType().equalsIgnoreCase(Constants.INDIVIDUAL)) {

				saveUser = userRepository.save(user);

				/*
				 * Checking the User, If the User is Company then inserting records into User,
				 * Company, Address and UserType tables.
				 */

			} else if (registrationDTO.getType().equalsIgnoreCase(Constants.COMPANY)) {

				user = companyRegistration(registrationDTO, user);
				saveUser = userRepository.save(user);
			}

			/* Inserting record into Login table */
			Login login = new Login();
			login.setUserId(saveUser.getUserId());
			login.setEmailId(registrationDTO.getEmailId());
			login.setPassword(encryptedPassword);
			loginRepository.save(login);

		} catch (Exception e) {
			logger.error("Exception raised in registration ", e);
			throw new AccredilinkException("Exception raised in registration");
		}

		boolean flag = notificationService.registrationNotification(registrationDTO);
		if (flag) {
			logger.info("Successfully sent email notification after registration.");
		} else {
			logger.error("ERROR : while sending notification at registration");
		}
		 
		ResponseDTO responseDTO  = new ResponseDTO();
		responseDTO.setMessage(Constants.REGISTRATION_SUCCESS);
		responseDTO.setStatudCode(HttpStatus.OK.value());
		return responseDTO;
	}

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	@Transactional
	public ResponseDTO login(String userName, String password) {

		ResponseDTO responseDTO  = new ResponseDTO();
		String encryptedPassword = EncriptAndDescript.encrypt(password);
		Optional<Login> optionalLogin = loginRepository.findByEmailIdAndPassword(userName, encryptedPassword);
		if (optionalLogin.isPresent()) {
			responseDTO.setMessage(Constants.LOGIN_SUCCESS);
			responseDTO.setStatudCode(HttpStatus.OK.value());
			return responseDTO;
		}
		responseDTO.setMessage(Constants.INVALID_CREDENTIALS);
		responseDTO.setStatudCode(HttpStatus.NOT_FOUND.value());
		return responseDTO;
	}

	private boolean isEmailValid(String emailId) {
		EmailValidator emailValidator = new EmailValidator();
		return emailValidator.validate(emailId);
	}

	@Transactional
	public ResponseDTO resetPassword(String emailId, String password, String confirmPassword) {
		Optional<Registration> optionalRegistration = registrationRepository.findByEmailId(emailId);
		if (!optionalRegistration.isPresent()) {
			throw new AccredilinkException(Constants.INVALID_EMAIL_ID);
		}
		Registration registration = optionalRegistration.get();
		try {
			registration.setPassword(password);
			registrationRepository.save(registration);

			/* Upadating password into Login table */
			Optional<Login> optionalLogin = loginRepository.findByEmailId(emailId);
			if (optionalLogin.isPresent()) {
				Login login = optionalLogin.get();
				login.setPassword(password);
				login.setConfirmPassword(confirmPassword);
				loginRepository.save(login);
			}
		} catch (Exception e) {
			logger.error("Exception raised in reset password : " + e);
			throw new AccredilinkException("Exceptin raised in reset password");
		}

		boolean flag = notificationService.resetPasswordNotification(emailId);
		if (flag) {
			logger.info("Successfully sent email notification for reset password");
		} else {
			logger.info("ERROR : while sending notification for reset password");
		}

		ResponseDTO responseDTO  = new ResponseDTO();
		responseDTO.setMessage(Constants.PASSWORD_CHANGED);
		responseDTO.setStatudCode(HttpStatus.CREATED.value());
		return responseDTO;
	}

	private User companyRegistration(RegistrationDTO registrationDTO, User user) {

		Address address = new Address();
		address.setAddress_line1(registrationDTO.getAddressLine1());
		address.setAddressLine2(registrationDTO.getAddressLine2());
		address.setCity(registrationDTO.getCity());
		address.setState(registrationDTO.getState());
		address.setCountry(registrationDTO.getCountry());
		address.setZip(registrationDTO.getZip());
		address.setCreatedDate(registrationDTO.getCreatedDate());
		address.setModifiedDate(registrationDTO.getModifiedDate());
		address.setCreatedBy(registrationDTO.getCreatedBy());
		address.setModifiedBy(registrationDTO.getModifiedBy());
		address.setActive(registrationDTO.isActive());

		Company company = new Company();
		company.setCompany_name(registrationDTO.getCompany_name());
		company.setEin(registrationDTO.getEin());
		company.setCreatedDate(registrationDTO.getCreatedDate());
		company.setModifiedDate(registrationDTO.getModifiedDate());
		company.setCreatedBy(registrationDTO.getCreatedBy());
		company.setModifiedBy(registrationDTO.getModifiedBy());
		company.setActive(registrationDTO.isActive());

		UserType userType = new UserType();
		userType.setUserTypeName(registrationDTO.getUserTypeName());
		userType.setUserTypeDescription(registrationDTO.getUserTypeDescription());
		userType.setCreatedDate(registrationDTO.getCreatedDate());
		userType.setModifiedDate(registrationDTO.getModifiedDate());
		userType.setCreatedBy(registrationDTO.getCreatedBy());
		userType.setModifiedBy(registrationDTO.getModifiedBy());
		userType.setActive(registrationDTO.isActive());

		user.setAddress(address);
		user.setCompany(company);
		user.setUserType(userType);

		return user;

	}
}
