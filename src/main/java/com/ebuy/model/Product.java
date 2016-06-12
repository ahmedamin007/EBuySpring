package com.ebuy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {

	@Id @GeneratedValue
	private int id;
	private String productName;
	private String desc;
	private double price ;
	@OneToOne
	private ProductType productType;
	// denormalized filed because  we don't implements stock control 
	private int stock;
	
	
	public Product(String productName, String desc, double price, ProductType productType, int stock) {
		super();
		this.productName = productName;
		this.desc = desc;
		this.price = price;
		this.productType = productType;
		this.stock = stock;
	}

	public Product() {
		
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public ProductType getProductType() {
		return productType;
	}


	public void setProductType(ProductType productType) {
		this.productType = productType;
	}




}
