package com.accredilink.bgv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accredilink.bgv.entity.AccrediEmployee;

@Repository
public interface EmployeeRepository extends JpaRepository<AccrediEmployee, Long> {
	
	Optional<AccrediEmployee> findBySsnNumber(String ssnNumber);
	
	List<AccrediEmployee> findByFirstNameOrLastNameOrEmailIdOrSsnNumber(String firstName, String lastName, String emailId, String ssnNumber);

}
