package com.accredilink.bgv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accredilink.bgv.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	
	Optional<Login> findByEmailIdAndPassword(String userName, String password);
	
	Optional<Login> findByEmailId(String emailId);

}
