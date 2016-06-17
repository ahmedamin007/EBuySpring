package com.ebuy.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Orderline")
public class Orderline {
	
	@Id @GeneratedValue
	private int id;
	private int quantity;
	@OneToOne
	private Product product;
	@ManyToOne
	private Order order;
	private double price;
	private double subtotal;
	
	
	public Orderline(Product product,int quantity, double price, double subtotal) {
		super();
		
		this.quantity = quantity;
		this.product = product;
		this.price = price;
		this.subtotal = subtotal;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public double getSubtotal() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return Double.valueOf(formatter.format(this.quantity*this.price))  ;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = product.getPrice();
	}

}
