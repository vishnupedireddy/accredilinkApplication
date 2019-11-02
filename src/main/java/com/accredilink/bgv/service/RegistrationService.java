package com.accredilink.bgv.service;

import com.accredilink.bgv.dto.RegistrationDTO;


/**
 * @author Vishnu vardhan reddy
 *
 */
public interface RegistrationService {
	
	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	public String registration(RegistrationDTO registrationDTO) throws Exception;
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public String login(String userName, String password);

}
