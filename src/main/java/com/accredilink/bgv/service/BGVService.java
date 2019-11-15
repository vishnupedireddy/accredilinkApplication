package com.accredilink.bgv.service;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.entity.Registration;
import com.accredilink.bgv.entity.User;

public interface BGVService {
	
	/**
	 * @param ssnNumber
	 * @return
	 */
	public User view(String roleType,Long userId);
	
	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	public Registration update(RegistrationDTO registrationDTO) throws Exception;
	
	/**
	 * @param ssnNumber
	 */
	public void delete(Long ssnNumber);

}
