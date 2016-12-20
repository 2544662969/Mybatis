package com.zhjg.mybatis.pojo;

public class Car {

	private int id;
	private String brand;
	private String type;
	private double price;
	
	
	
	public Car() {
		super();
	}
	public Car(String brand, String type, double price) {
		super();
		this.brand = brand;
		this.type = type;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
