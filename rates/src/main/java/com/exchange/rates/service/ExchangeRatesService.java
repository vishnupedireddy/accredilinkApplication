package com.exchange.rates.service;

import java.time.LocalDate;

import com.exchange.rates.dto.ExchangeRatesInfoDTO;

public interface ExchangeRatesService {
	
	
	public void insertExchangeRateInfo(ExchangeRatesInfoDTO exchangeRatesInfo);
	
	public ExchangeRatesInfoDTO retiveExchangeRateInfo(Long date);

}
