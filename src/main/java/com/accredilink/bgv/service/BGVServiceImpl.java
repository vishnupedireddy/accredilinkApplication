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
import com.accredilink.bgv.exception.CustomException;
import com.accredilink.bgv.repository.RegistrationRepository;

@Service
public class BGVServiceImpl implements BGVService{
	
	private static final Logger logger = LoggerFactory.getLogger(BGVServiceImpl.class);
	
	@Autowired
	RegistrationRepository registrationRepository;

	/**
	 * @param ssnNumber
	 * @return
	 */
	@Transactional
	public Registration view(Long ssnNumber) {
		Optional<Registration> registratin = registrationRepository.findById(ssnNumber);
		if(!registratin.isPresent()) {
			throw new CustomException("Invalid SSN Number");
		}
		return registratin.get();
	}

	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Registration update(RegistrationDTO registrationDTO) throws Exception{
		
		Optional<Registration> optionalRegistration = registrationRepository.findById(registrationDTO.getSsnNumber());
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
