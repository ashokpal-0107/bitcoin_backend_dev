package com.example.refactor;

public class INRConversionStrategy implements CurrencyConversionStrategy {
    @Override
    public double convert(double priceInUsd) {
        // Assume conversion rate for INR (just an example)
        double conversionRate = 74.85;
        return priceInUsd * conversionRate;
    }
}