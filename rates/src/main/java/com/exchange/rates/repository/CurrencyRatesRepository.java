package com.exchange.rates.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exchange.rates.entity.CurrencyRates;

@Repository
public interface CurrencyRatesRepository extends CrudRepository<CurrencyRates, Long> {
	
	List<CurrencyRates> findByApiKey(Long apiKey);

}
