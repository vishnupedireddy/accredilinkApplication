package com.accredilink.bgv.service;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.dto.ResponseDTO;


public interface RegistrationService {
	
	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	public ResponseDTO registration(RegistrationDTO registrationDTO) throws Exception;
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public ResponseDTO login(String userName, String password);
	
	public ResponseDTO resetPassword(String emailId, String password, String confirmPassword);

}
