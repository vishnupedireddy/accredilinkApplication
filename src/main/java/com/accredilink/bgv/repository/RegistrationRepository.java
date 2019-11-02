package com.accredilink.bgv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accredilink.bgv.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	
	Optional<Registration> findByEmailId(String emailId);

}
