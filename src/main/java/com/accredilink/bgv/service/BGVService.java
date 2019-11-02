package com.accredilink.bgv.service;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.entity.Registration;


/**
 * @author Vishnu vardhan reddy
 *
 */
public interface BGVService {
	
	/**
	 * @param ssnNumber
	 * @return
	 */
	public Registration view(Long ssnNumber);
	
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
