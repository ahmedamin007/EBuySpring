/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebuy.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author ahmedamin
 */

@Entity
@Table(name="Person")
public class Person {
    
    @Id @GeneratedValue
    private int id;
    private String username;
    private String password;
	private String firstName;
	private String lastName;
	private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    @OneToMany(cascade = CascadeType.ALL , fetch=FetchType.EAGER)
    private List<Address> address;
	private boolean admin;
	
    
	public Person() {
    }

	public Person(String username, String password, String firstName, String lastName, String email, String phone,
			EGender gender, List<com.ebuy.model.Address> address, boolean admin) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public EGender getGender() {
		return gender;
	}


	public void setGender(EGender gender) {
		this.gender = gender;
	}


	public List<Address> getAddress() {
		return address;
	}


	public void setAddress(List<Address> address) {
		this.address = address;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}




    


    
    
}
