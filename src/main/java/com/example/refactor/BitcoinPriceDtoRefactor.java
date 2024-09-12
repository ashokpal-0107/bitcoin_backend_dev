package com.example.refactor;

public class BitcoinPriceDtoRefactor {

    private String date;
    private double price;
    private boolean highest;
    private boolean lowest;

    private BitcoinPriceDtoRefactor(Builder builder) {
        this.date = builder.date;
        this.price = builder.price;
        this.highest = builder.highest;
        this.lowest = builder.lowest;
    }

    public static class Builder {
        private String date;
        private double price;
        private boolean highest;
        private boolean lowest;

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setHighest(boolean highest) {
            this.highest = highest;
            return this;
        }

        public Builder setLowest(boolean lowest) {
            this.lowest = lowest;
            return this;
        }

        public BitcoinPriceDtoRefactor build() {
            return new BitcoinPriceDtoRefactor(this);
        }
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHighest() {
        return highest;
    }

    public boolean isLowest() {
        return lowest;
    }

    public void setHighest(boolean highest) {
        this.highest = highest;
    }

    public void setLowest(boolean lowest) {
        this.lowest = lowest;
    }


}
