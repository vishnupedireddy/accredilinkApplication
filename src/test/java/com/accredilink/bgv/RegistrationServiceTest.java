
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
import com.accredilink.bgv.entity.Login;
import com.accredilink.bgv.entity.Registration;
import com.accredilink.bgv.repository.LoginRepository;
import com.accredilink.bgv.repository.RegistrationRepository;
import com.accredilink.bgv.service.RegistrationServiceImpl;

@RunWith(MockitoJUnitRunner.class)

@SpringBootTest
public class RegistrationServiceTest {

	@InjectMocks
	RegistrationServiceImpl registrationService;

	@Mock
	RegistrationRepository registrationRepository;

	@Mock
	LoginRepository loginRepository;

	RegistrationDTO registrationDTO = null;
	Login login = null;
	Registration registration = null;

	@Before
	public void setUp() {

		registrationDTO = new RegistrationDTO();
		registrationDTO.setSsnNumber("5180");
		registrationDTO.setFirstName("vishnu");
		registrationDTO.setLastName("reddy");
		registrationDTO.setUserRole("user");
		registrationDTO.setEmailId("vis@gmail.com");

		login = new Login();
		login.setPassword("vis");
		login.setConfirmPassword("vis@gmail.com");

		registration = new Registration();
		registration.setSsnNumber(5180L);
	}

	@Test
	public void testLogin() {

		Optional<Login> optionalLogin = Optional.of(login);
		Mockito.when(loginRepository.findByEmailIdAndPassword(login.getEmailId(), login.getPassword()))
				.thenReturn(optionalLogin);
		String response = registrationService.login(login.getEmailId(), login.getPassword());
		Assert.assertNotNull(response);
		Assert.assertEquals(response, "Login success");
	}

	@Test
	public void testResetPassword() {

		Optional<Registration> optionalRegistration = Optional.of(registration);
		Mockito.when(registrationRepository.findByEmailId(registration.getEmailId())).thenReturn(optionalRegistration);
		String response = registrationService.resetPassword(registration.getEmailId(), login.getPassword(),
				login.getConfirmPassword());
		Assert.assertNotNull(response);
		Assert.assertEquals(response, "Successfully password is changed.");
	}

}
