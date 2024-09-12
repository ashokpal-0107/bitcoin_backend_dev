package com.example.refactor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BitcoinPriceServiceRefactor {

    public List<BitcoinPriceDtoRefactor> getBitcoinPrices(String startDate, String endDate, String currency) {
        List<BitcoinPriceDtoRefactor> historicalPrices = fetchHistoricalPrices(startDate, endDate);
        
        // Identify the highest and lowest prices
        double highest = historicalPrices.stream().mapToDouble(BitcoinPriceDtoRefactor::getPrice).max().orElse(0);
        double lowest = historicalPrices.stream().mapToDouble(BitcoinPriceDtoRefactor::getPrice).min().orElse(0);

        // Mark prices as highest/lowest
        historicalPrices.forEach(price -> {
            price.setHighest(price.getPrice() == highest);
            price.setLowest(price.getPrice() == lowest);
        });

        // Apply currency conversion
        CurrencyConversionStrategy conversionStrategy = CurrencyConversionFactory.getStrategy(currency);
       // historicalPrices.forEach(price -> price.setPrice(conversionStrategy.convert(price.getPrice())));

        return historicalPrices;
    }

    public List<BitcoinPriceDtoRefactor> fetchHistoricalPrices(String startDate, String endDate) {
        List<BitcoinPriceDtoRefactor> bitCoinList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate1 = LocalDate.parse(startDate, formatter);
        LocalDate endDate1 = LocalDate.parse(endDate, formatter);
        
        // Use factory to create HttpClient
        HttpClient client = HttpClientFactory.createHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.coindesk.com/v1/bpi/historical/close.json"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode bpiNode = rootNode.path("bpi");

            Iterator<Map.Entry<String, JsonNode>> fields = bpiNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String dateString = field.getKey();
                double price = field.getValue().asDouble();
                LocalDate date = LocalDate.parse(dateString, formatter);

                // Check if the date is within range
                if (!date.isBefore(startDate1) && !date.isAfter(endDate1)) {
                    bitCoinList.add(
                            new BitcoinPriceDtoRefactor.Builder()
                                    .setDate(dateString)
                                    .setPrice(price)
                                    .build()
                    );
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return bitCoinList;
    }
}
