
package com.accredilink.bgv;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.accredilink.bgv.dto.RegistrationDTO;
import com.accredilink.bgv.entity.Registration;
import com.accredilink.bgv.entity.User;
import com.accredilink.bgv.repository.RegistrationRepository;
import com.accredilink.bgv.service.BGVServiceImpl;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
public class BGVServiceTest {

	@InjectMocks
	BGVServiceImpl bgvServiceImpl;

	@Mock
	RegistrationRepository registrationRepository;

	Registration registration = null;
	RegistrationDTO registrationDTO = null;

	@Before
	public void setUp() {

		registration = new Registration();
		registration.setSsnNumber(5180L);

		registrationDTO = new RegistrationDTO();
		registrationDTO.setSsnNumber("5180");
		registrationDTO.setFirstName("vishnu");
		registrationDTO.setLastName("reddy");
		registrationDTO.setUserRole("user");
		registrationDTO.setEmailId("vis@gmail.com");

	}

	@Test
	public void testView() {

		Optional<Registration> optionalRegistration = Optional.of(registration);
		Mockito.when(registrationRepository.findById(5180L)).thenReturn(optionalRegistration);
		User r = bgvServiceImpl.view("individual",12L);
		Assert.assertNotNull(r);
		Assert.assertEquals(r.getSsnNumber(), registration.getSsnNumber());
	}

	@Test
	public void testUPdate() throws Exception {

		Optional<Registration> optionalRegistration = Optional.of(registration);
		Mockito.when(registrationRepository.findById(5180L)).thenReturn(optionalRegistration);
		Mockito.when(registrationRepository.save(registration)).thenReturn(registration);
		Registration registration = bgvServiceImpl.update(registrationDTO);
		Assert.assertNotNull(registration);
	}

	@Test
	public void testDelete() {

		Optional<Registration> optionalRegistration = Optional.of(registration);
		Mockito.when(registrationRepository.findById(5180L)).thenReturn(optionalRegistration);
		bgvServiceImpl.delete(5180L);
	}
}
