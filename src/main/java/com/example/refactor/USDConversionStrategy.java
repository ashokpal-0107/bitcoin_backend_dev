package com.example.refactor;

public class USDConversionStrategy implements CurrencyConversionStrategy {
    @Override
    public double convert(double priceInUsd) {
        return priceInUsd; // USD, so no conversion needed.
    }
}




