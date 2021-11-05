package com.exchange.rates.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class ExchangeRatesInfoDTO {
	
	private LocalTime timeStamp;
	
	private String base;
	
	private LocalDate date;
	
	private Map<String, Float> ratesMap;

	public LocalTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Map<String, Float> getRatesMap() {
		return ratesMap;
	}

	public void setRatesMap(Map<String, Float> ratesMap) {
		this.ratesMap = ratesMap;
	}
	
	

}
