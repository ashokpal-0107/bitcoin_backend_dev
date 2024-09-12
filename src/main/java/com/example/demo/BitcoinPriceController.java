package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class BitcoinPriceController {
	
	 @Autowired
	    private BitcoinPriceService bitcoinPriceService;
	 @Autowired
	    private BitcoinPriceServiceWithOffline bitcoinPriceServiceOffLine;

	    @GetMapping("/bitcoin-price")
	    public ResponseEntity<List<BitcoinPriceDto>> getBitcoinPrice(
	        @RequestParam String startDate,
	        @RequestParam String endDate,
	        @RequestParam String currency,
	        @RequestParam(defaultValue = "false") boolean offLine) {
	    	
	    	List<BitcoinPriceDto> prices = new   ArrayList<>();
	        
	    	if(!offLine) {
		      prices = bitcoinPriceService.getBitcoinPrices(startDate, endDate, currency);

	    	}else {
	    	   prices = bitcoinPriceServiceOffLine.getBitcoinPrices(startDate, endDate, currency);
	    	}
	        return ResponseEntity.ok(prices);
	    }
}
