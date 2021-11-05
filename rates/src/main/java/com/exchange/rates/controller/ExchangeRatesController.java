package com.exchange.rates.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.rates.dto.ExchangeRatesInfoDTO;
import com.exchange.rates.service.ExchangeRatesService;

@RestController
public class ExchangeRatesController {
	
	@Autowired
	ExchangeRatesService service;
	
	@PostMapping("insert")
	public String insertExchangeRateInfo() {
		
		ExchangeRatesInfoDTO exchangeRatesInfo = new ExchangeRatesInfoDTO();
		
		exchangeRatesInfo.setBase("EUR");
		
		Map<String, Float> map = new HashMap<String, Float>();
		map.put("AUD", 1.566015f);
		map.put("CAD", 1.560132f);
		map.put("CHF", 1.154727f);
		map.put("CNY", 7.827874f);
		map.put("GBP", 0.882047f);
		
		exchangeRatesInfo.setRatesMap(map);
		
		service.insertExchangeRateInfo(exchangeRatesInfo);
		
		return "Successfully inserted";
		
	}

	@GetMapping("retrieve/{apiKey}")
	public ExchangeRatesInfoDTO retiveExchangeRateInfo(@PathVariable Long apiKey) {
		
		return service.retiveExchangeRateInfo(apiKey);
	}

}
