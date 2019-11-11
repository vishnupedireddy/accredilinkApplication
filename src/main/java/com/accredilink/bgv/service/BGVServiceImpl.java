package com.accredilink.bgv.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.entity.Registration;
import com.accredilink.bgv.entity.User;
import com.accredilink.bgv.exception.CustomException;
import com.accredilink.bgv.repository.RegistrationRepository;
import com.accredilink.bgv.repository.UserRepository;
import com.accredilink.bgv.util.Constants;

@Service
public class BGVServiceImpl implements BGVService{
	
	private static final Logger logger = LoggerFactory.getLogger(BGVServiceImpl.class);
	
	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	UserRepository userRepository;

	/**
	 * @param ssnNumber
	 * @return
	 */
	@Transactional
	public User view(String roleType, Long userId) {
		
		Optional<User> optionalUser = userRepository.findById(userId);
		if(!optionalUser.isPresent()) {
			throw new CustomException("Invalid user id");
		}
		
		User user = optionalUser.get();

		if (roleType.equalsIgnoreCase(Constants.INDIVIDUAL)) {
			user.setAddress(null);
			user.setCompany(null);
			user.setUserType(null);
		} else if (roleType.equalsIgnoreCase(Constants.COMPANY)) {
			user.setSsnNumber("");
		}
 
		return user;
	}

	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Registration update(RegistrationDTO registrationDTO) throws Exception{
		
		Optional<Registration> optionalRegistration = registrationRepository.findById(Long.valueOf(registrationDTO.getSsnNumber()));
		if(!optionalRegistration.isPresent()) {
			throw new CustomException("Invalid SSN number");
		}
		
		Registration registration = optionalRegistration.get();
		BeanUtils.copyProperties(registration, registrationDTO);
		try {
			registration = registrationRepository.save(registration);
		} catch (Exception e) {
			logger.error("Exception occured in updating employee : " + registrationDTO.getSsnNumber());
		}
		return registration;
	}

	/**
	 * @param ssnNumber
	 */
	@Transactional
	public void delete(Long ssnNumber) {
		Optional<Registration> registratin = registrationRepository.findById(ssnNumber);
		if(!registratin.isPresent()) {
			throw new CustomException("Invalid SSN Number");
		}
		try {
			registrationRepository.deleteById(ssnNumber);
		} catch (Exception e) {
			logger.error("Exception occured in deleting employee : " + ssnNumber);
		}
	}

}
