/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebuy.model;


import java.util.List;

import javax.persistence.Entity;
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
    private String name ;
    private String phone;
    private EGender gender;
    @OneToMany
    private List<Address> address;

    
	public Person() {
    }

    
    public Person( String username, String password, String name, String phone, EGender gender, List<Address> address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
    }

    public int getId() {
        return id;
    }

	public List<Address> getAddress() {
		return address;
	}


	public void setAddress(List<Address> address) {
		this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    
    
}
