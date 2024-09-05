package com.example.demo;

public class BitcoinPriceDto {
	private String date;
    private double price;
    private boolean highest;
    private boolean lowest;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isHighest() {
		return highest;
	}
	public void setHighest(boolean highest) {
		this.highest = highest;
	}
	public boolean isLowest() {
		return lowest;
	}
	public void setLowest(boolean lowest) {
		this.lowest = lowest;
	}

}
