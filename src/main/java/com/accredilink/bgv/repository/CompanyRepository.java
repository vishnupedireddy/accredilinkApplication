package com.accredilink.bgv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accredilink.bgv.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
