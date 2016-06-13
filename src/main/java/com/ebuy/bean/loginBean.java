package com.ebuy.bean;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Component;

@Component
@ManagedBean
@SessionScoped
public class loginBean {

	private String username;
	private String passowrd;
	private boolean admin;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public loginBean() {
		
	}

}
