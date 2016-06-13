package com.ebuy.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Component;

@Component
@ManagedBean
@RequestScoped
public class adminNavBean {
	
	private String viewName="";
	

	public adminNavBean() {
		
	}


	public String getViewName() {
		return viewName;
	}
	

	public String openviewProductType(){
		
		viewName="/productType.xhtml";
		System.out.println("enter 1" + viewName);
		return "/adminHome.jsf";
	}

	public String  openviewProduct(){
		viewName="/product.xhtml";
		System.out.println("enter 2 " + viewName);
		return "/adminHome.jsf";
	}
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	


}
