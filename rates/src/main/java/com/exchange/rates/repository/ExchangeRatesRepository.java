package com.exchange.rates.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exchange.rates.entity.ExchangeRatesInfo;

@Repository
public interface ExchangeRatesRepository extends CrudRepository<ExchangeRatesInfo, Long> {
	

	
}
