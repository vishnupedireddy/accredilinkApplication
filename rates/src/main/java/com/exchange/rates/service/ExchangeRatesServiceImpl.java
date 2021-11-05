package com.exchange.rates.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.rates.dto.ExchangeRatesInfoDTO;
import com.exchange.rates.entity.CurrencyRates;
import com.exchange.rates.entity.ExchangeRatesInfo;
import com.exchange.rates.repository.CurrencyRatesRepository;
import com.exchange.rates.repository.ExchangeRatesRepository;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {
	
	@Autowired
	ExchangeRatesRepository repository;
	
	@Autowired
	CurrencyRatesRepository currencyRepository;

	@Override
	@Transactional
	public void insertExchangeRateInfo(ExchangeRatesInfoDTO dto) {
		
		try {
			
			ExchangeRatesInfo info = new ExchangeRatesInfo();
			
			info.setBase(dto.getBase());
			info.setDate(LocalDate.now());
			info.setTimeStamp(LocalTime.now());
			
			ExchangeRatesInfo storedInfo = repository.save(info);
			
			Map<String, Float> ratesMap = dto.getRatesMap();
			if(ratesMap != null) {
				
				for(Entry<String, Float> entry : ratesMap.entrySet()) {
					
					CurrencyRates currencyRates = new CurrencyRates(entry.getKey(), entry.getValue());
					currencyRates.setApiKey(storedInfo.getApiKey());
					currencyRepository.save(currencyRates);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	@Transactional
	public ExchangeRatesInfoDTO retiveExchangeRateInfo(Long key) {
		
		Optional<ExchangeRatesInfo> info = repository.findById(key);
		ExchangeRatesInfo rateInfo = null;
		if(info.isPresent()){
		     rateInfo = info.get();
		}
		
		List<CurrencyRates> rates = currencyRepository.findByApiKey(key);
		
		Map<String, Float> map = new HashMap<String, Float>();
		if(rates != null) {
			for(CurrencyRates r : rates) {
				map.put(r.getCode(), r.getPrice());
			}
		}
		
		ExchangeRatesInfoDTO finalInfo = new ExchangeRatesInfoDTO();
		finalInfo.setBase(rateInfo.getBase());
		finalInfo.setDate(rateInfo.getDate());
		
		finalInfo.setTimeStamp(rateInfo.getTimeStamp());
		finalInfo.setRatesMap(map);
		
		return finalInfo;
	}
}
