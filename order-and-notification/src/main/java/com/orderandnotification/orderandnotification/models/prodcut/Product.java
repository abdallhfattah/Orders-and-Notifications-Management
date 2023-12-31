package com.orderandnotification.orderandnotification.models.prodcut;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {
	@JsonIgnore
	private int serialNumber;
//	@JsonProperty("name")
	private String name;
	private String vendor;
	private String category;
	private double price;

	public Product(int serialNumber, String name, String vendor, String category, double price) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.vendor = vendor;
		this.category = category;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
