package com.exchange.rates.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EXCHANGE_RATES_INFO")
public class ExchangeRatesInfo {
	
	@Id
	@Column(name = "API_KEY")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long apiKey;
	
	@Column(name = "TIMESTAMP")
	private LocalTime timeStamp;
	
	@Column(name = "BASE")
	private String base;
	
	@Column(name = "DATE")
	private LocalDate date;
	
	/*
	 * @OneToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "id") private List<CurrencyRates> rateList;
	 */

	public Long getApiKey() {
		return apiKey;
	}

	public void setApiKey(Long apiKey) {
		this.apiKey = apiKey;
	}

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

	/*
	 * public List<CurrencyRates> getRateList() { return rateList; }
	 * 
	 * public void setRateList(List<CurrencyRates> rateList) { this.rateList =
	 * rateList; }
	 */
}
