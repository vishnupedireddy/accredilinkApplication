package com.accredilink.bgv.service;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.dto.ResponseObject;


public interface RegistrationService {
	
	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	public ResponseObject registration(RegistrationDTO registrationDTO) throws Exception;
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public ResponseObject login(String userName, String password);
	
	public ResponseObject resetPassword(String emailId, String password, String confirmPassword);

}
