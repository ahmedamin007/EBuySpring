package com.ebuy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProductType")
public class ProductType {




	@Id @GeneratedValue
	private int id;
	private String name;
	@Column(name = "ItemDesc")
	private String desc;
	
	
	public ProductType() {

	}

	
	
	public ProductType(int id, String name, String desc) {
		super();
		this.id=id;
		this.name = name;
		this.desc = desc;
	}

	@Override
	public String toString() {
		return this.name ;
	}
	
	public ProductType( String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}




}
