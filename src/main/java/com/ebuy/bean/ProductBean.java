package com.ebuy.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebuy.model.Product;
import com.ebuy.service.ProductService;
import com.ebuy.service.ProductTypeService;

@Component
@ManagedBean
@RequestScoped
public class ProductBean {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductTypeService productTypeService;

	Product product=new Product();
	

//	private int id;
//	private String productName;
//	private String desc;
//	private double price ;
//	private ProductType productType;
//	private int stock;

	
	public ProductService getProductService() {
		return productService;
	}


	public String edit(int id){
		
		this.product.setProductName(productService.findById(id).getProductName()); 
		this.product.setDesc(productService.findById(id).getDesc()); 
		this.product.setPrice(productService.findById(id).getPrice()); 
		this.product.setStock(productService.findById(id).getStock());
		this.product.setProductType(productService.findById(id).getProductType());
		
		return "/editProduct.jsf";
	}
	
	public String save(){
		if (this.product.getProductName().equals("") || this.product.getProductName()==null || 
				this.product.getDesc().equals("") || this.product.getDesc()==null ){
			
        	return "/newProduct.jsf";
		}
		
		productService.update(this.product.getProductName(), this.product.getDesc(), 
				this.product.getPrice(), this.product.getProductType(), this.product.getStock() ,this.product.getId());
		
		return "/product.jsf";
	}
	

	public String saveNew(){
		System.out.println("enter 1");
		if (this.product.getProductName().equals("") || this.product.getProductName()==null || 
				this.product.getDesc().equals("") || this.product.getDesc()==null ){
			
        	return "/newProduct.jsf";
		}
			
		productService.save(new Product(this.product.getProductName(),  this.product.getDesc(), this.product.getPrice(), 
				this.product.getProductType(), this.product.getStock()));
		
		this.product.setPrice(0);
		this.product.setProductName("");
		this.product.setDesc("");
		this.product.setStock(0);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful",  "Successful Saved... " ) );
		 
		return "/newProduct.jsf";
	}


	public String add(){

		this.product.setPrice(0);
		this.product.setProductName("");
		this.product.setDesc("");
		this.product.setStock(0);

		return "/newProduct.jsf";
	}
	
	public String delete(int id){

		
		productService.delete(productService.findById(id));
		return "/productType.jsf";
	}
	
	public List<Product> getAllProduct() {
		return productService.findAll();
	}

	
	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public ProductBean() {
		
	}

}
