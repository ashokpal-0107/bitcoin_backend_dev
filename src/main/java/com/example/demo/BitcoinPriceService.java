package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BitcoinPriceService {
	
	public List<BitcoinPriceDto> getBitcoinPrices(String startDate, String endDate, String currency) {
        List<BitcoinPriceDto> historicalPrices = fetchHistoricalPrices(startDate, endDate);
        
        double highest = historicalPrices.stream().mapToDouble(BitcoinPriceDto::getPrice).max().orElse(0);
        double lowest = historicalPrices.stream().mapToDouble(BitcoinPriceDto::getPrice).min().orElse(0);

        historicalPrices.forEach(price -> {
            if (price.getPrice() == highest) price.setHighest(true);
            if (price.getPrice() == lowest) price.setLowest(true);
        });

        if (!currency.equals("USD")) {
            //double conversionRate = getConversionRate(currency);
            historicalPrices.forEach(price -> price.setPrice(price.getPrice() ));
        }

        return historicalPrices;
    }

    public List<BitcoinPriceDto> fetchHistoricalPrices(String startDate, String endDate) {
    	List<BitcoinPriceDto>  bitCoinList =  new  ArrayList<>();
    	
    	Map<String,String>  coinList =  new  HashMap();
  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate1 = LocalDate.parse(startDate, formatter);
        LocalDate endDate1 = LocalDate.parse(endDate, formatter);
    	HttpClient client = HttpClient.newHttpClient();

		// Step 2: Create an HttpRequest for the CoinDesk API
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create("https://api.coindesk.com/v1/bpi/historical/close.json"))
		        .build();

		// Step 3: Send the request and get the response
		HttpResponse<String> response;
		try {

			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			// Step 4: Parse the JSON response using Jackson
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(response.body());

			// Navigate to the "bpi" node which contains the prices
			JsonNode bpiNode = rootNode.path("bpi");
			

			Iterator<Map.Entry<String, JsonNode>> fields = bpiNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();

                String dateString = field.getKey();
                double price = field.getValue().asDouble();

                // Parse the date string to LocalDate for comparison
                LocalDate date = LocalDate.parse(dateString, formatter);

                // Step 5: Check if the date falls within the given range
                if (!date.isBefore(startDate1) && !date.isAfter(endDate1)) {
                	
					
                    // Print the date and the price
                	coinList.put(dateString, Double.toString(price));
                	//bitCoinList .add(bitCoin);
                }
            }
            String highestPrice = null;
            String lowestPrice = null ;
            if(!coinList.isEmpty()) {
            	 highestPrice = Collections.max(coinList.values());
    	         lowestPrice = Collections.min(coinList.values());
            	
            }
			
            
            indentifyPrice(bitCoinList, coinList, highestPrice, lowestPrice);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bitCoinList;

    }

	private void indentifyPrice(List<BitcoinPriceDto> bitCoinList, Map<String, String> coinList, String highestPrice,
			String lowestPrice) {
		for (Map.Entry<String, String> set :
			coinList.entrySet()) {
			
			
		  BitcoinPriceDto bitCoin = new BitcoinPriceDto() ;

		String price = set.getValue();
		bitCoin.setDate(set.getKey());
		bitCoin.setPrice(Double.parseDouble(price));
		// Check for the highest and lowest prices
		if (price == highestPrice) {
		    bitCoin.setHighest(true);
		    bitCoin.setLowest(false);
		}
		if (price == lowestPrice) {
		    bitCoin.setLowest(true);
		    bitCoin.setHighest(false);
		}
   
		   // Printing all elements of a Map

		   bitCoinList .add(bitCoin);
         }
	}

	/*
	 * private double getConversionRate(String currency) { // Fetch currency
	 * conversion rate from Coindesk API return 1.0; }
	 */

}
