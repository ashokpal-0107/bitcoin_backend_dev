package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BitcoinPriceServiceTest {
	
	 @Autowired
	    private BitcoinPriceService service;
	 
	 @Test
	    public void testGetBitcoinPrices() {
	        List<BitcoinPriceDto> prices = service.getBitcoinPrices("2024-08-10", "2024-08-20", "USD");
	        assertNotNull(prices);
	    }
	 @Test
	    public void testFetchHistoricalPrices() {
	        List<BitcoinPriceDto> prices = service.fetchHistoricalPrices("2024-08-10", "2024-08-20");
	        assertNotNull(prices);
	    }

}
