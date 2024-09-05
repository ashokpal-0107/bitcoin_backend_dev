package com.example.demo;

import java.util.List;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class BitcoinPriceServiceWithOffline {
	
	private Map<String, List<BitcoinPriceDto>> cache = new HashMap<>();
	
	private static final Map<String ,Double> offlinedata = new HashMap<>();
	
	static{
		
		offlinedata.put("2024-08-10", 61102.5242);
		offlinedata.put("2024-08-11", 60102.5242);
		offlinedata.put("2024-08-12", 55102.5242);
		offlinedata.put("2024-08-13", 59102.5242);
		offlinedata.put("2024-08-14", 45102.5242);
		
	}

    public List<BitcoinPriceDto> getBitcoinPrices(String startDate, String endDate, String currency) {
        List<BitcoinPriceDto> historicalPrices;

        try {
            historicalPrices = fetchHistoricalPrices(startDate, endDate);
            cache.put(generateCacheKey(startDate, endDate, currency), historicalPrices);
        } catch (Exception  e) {
            historicalPrices = cache.getOrDefault(generateCacheKey(startDate, endDate, currency), new ArrayList<>());
        }

        return historicalPrices;
    }

	private String generateCacheKey(String startDate, String endDate, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<BitcoinPriceDto> fetchHistoricalPrices(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	

   

}
