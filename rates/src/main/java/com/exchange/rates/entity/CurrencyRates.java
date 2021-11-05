package com.exchange.rates.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_RATES")
public class CurrencyRates {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "PRICE")
	private float price;
	
	private Long apiKey;
	
	public CurrencyRates(String code, float price) {
		super();
		this.code = code;
		this.price = price;
	}

	/*
	 * @ManyToOne private ExchangeRatesInfo exchangeRatesInfo;
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Long getApiKey() {
		return apiKey;
	}

	public void setApiKey(Long apiKey) {
		this.apiKey = apiKey;
	}

	/*
	 * public ExchangeRatesInfo getExchangeRatesInfo() { return exchangeRatesInfo; }
	 * 
	 * public void setExchangeRatesInfo(ExchangeRatesInfo exchangeRatesInfo) {
	 * this.exchangeRatesInfo = exchangeRatesInfo; }
	 */
}
