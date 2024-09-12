package com.example.demo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitcoinPriceServiceWithOffline {
	
	@Autowired
    private BitcoinPriceService bitcoinPriceService;

	List<BitcoinPriceDto>  prices =  new  ArrayList<>();
	private Map<String, List<BitcoinPriceDto>> priceCache  = new HashMap<>();
	

    public List<BitcoinPriceDto> getBitcoinPrices(String startDate, String endDate, String currency) {
        List<BitcoinPriceDto> historicalPrices;

        try {
            historicalPrices = fetchHistoricalPrices(startDate, endDate, currency);
            //priceCache.put(generateCacheKey(startDate, endDate, currency), historicalPrices);
        } catch (Exception  e) {
            historicalPrices = priceCache.getOrDefault(generateCacheKey(startDate, endDate, currency), new ArrayList<>());
        }

        return historicalPrices;
    }

    public static String generateCacheKey(String startDate, String endDate, String currency) {
        String key = startDate + "_" + endDate + "_" + currency;
        return hashKey(key); // Optionally, hash the key to ensure uniqueness
    }
 // Utility to hash the key using SHA-256 for better distribution and uniqueness
    private static String hashKey(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating cache key hash", e);
        }
    }

	private List<BitcoinPriceDto> fetchHistoricalPrices(String startDate, String endDate, String currency) {

		String cacheKey = generateCacheKey(startDate, endDate, currency);
		// Check if data is already cached
        if (priceCache.containsKey(cacheKey)) {
            return priceCache.get(cacheKey);
        }
     // Otherwise, fetch from the historical data source (CoinDesk API or another service)
       prices = bitcoinPriceService.fetchHistoricalPrices(startDate, endDate);

        // Store the result in cache
        priceCache.put(cacheKey, prices);

        return prices;
		
	}
	

   

}
