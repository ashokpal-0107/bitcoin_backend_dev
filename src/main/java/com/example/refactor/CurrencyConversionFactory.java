package com.example.refactor;

public class CurrencyConversionFactory {
    public static CurrencyConversionStrategy getStrategy(String currency) {
        if ("USD".equalsIgnoreCase(currency)) {
            return new USDConversionStrategy();
        } else if ("INR".equalsIgnoreCase(currency)) {
            return new INRConversionStrategy();
        }
        throw new IllegalArgumentException("Currency not supported");
    }
}