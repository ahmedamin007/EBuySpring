package com.ebuy.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebuy.model.ProductType;
import com.ebuy.service.ProductTypeService;


@Component
@ManagedBean
@RequestScoped
public class productTypeBean {

	@Autowired
	ProductTypeService productTypeService;
	
	private int id;
	private String name;
	private String desc;

	

	public productTypeBean() {
		
	}

	public String edit(int id){
		
		this.id=productTypeService.findById(id).getId();
		this.name=productTypeService.findById(id).getName();
		this.desc=productTypeService.findById(id).getDesc();
		return "/editProductType.jsf";
	}
	
	public String save(){
		if (this.name.equals("") || this.name==null || this.desc.equals("") || this.desc==null ){
        	return "/newProductType.jsf";
		}
		
		productTypeService.update(this.name, this.desc, this.id);
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage("Successful",  "Successful Saved... " ) );
		return "/productType.jsf";
	}
	

	public String saveNew(){
		System.out.println("enter 1");
		if (this.name.equals("") || this.name==null || this.desc.equals("") || this.desc==null ){
//			System.out.println("enter 2");
//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Please enter the name", "Error in add");
//        	RequestContext.getCurrentInstance().showMessageInDialog(message);
        	return "/newProductType.jsf";
		}
			
		productTypeService.save(new ProductType(this.name, this.desc));
		
		this.name="";
		this.desc="";
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful",  "Successful Saved... " ) );
		 
		return "/newProductType.jsf";
	}


	public String add(){

		this.id=0;
		this.name="";
		this.desc="";

		return "/newProductType.jsf";
	}
	
	public String delete(int id){
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Are you sure you want to delete this category?", "Echoes in eternity.");
//        
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
		
		productTypeService.delete(productTypeService.findById(id));
		return "/productType.jsf";
	}
	
	public List<ProductType> getAllProductType() {
		return productTypeService.findAll();
	}

	
	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}
	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
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
