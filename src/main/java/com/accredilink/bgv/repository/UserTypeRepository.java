package com.accredilink.bgv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accredilink.bgv.entity.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

}
