package com.ebuy.bean;



import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ebuy.model.Product;
import com.ebuy.service.ProductService;
import com.ebuy.service.ProductTypeService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
// ahmed amin

@Component
@ManagedBean
@RequestScoped
public class ProductBean {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductTypeService productTypeService;

	
	private String selection;

	Product product=new Product();
	private int prodPK;
	
	private String destination="//Volumes//D//Spring//EBuySpring//src//main//webapp//resources//images//";
	
    public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}


	public void copyFile(String fileName, InputStream in) {
        try {

        	 //String absolutePath= FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        	 //System.out.println("enter img absolutePath= " + absolutePath);

        	 //System.out.println("enter img absolutePath= " + destination + prodPK + ".jpg" ) ;
        	 
             OutputStream out = new FileOutputStream(new File(destination + prodPK + ".jpg" ));
           
             int read = 0;
             byte[] bytes = new byte[1024];
             System.out.println("enter img 2.3");
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
             in.close();
             out.flush();
             out.close();
           
             } catch (IOException e) {
             System.out.println(e.getMessage());
             }
    }
    
	public boolean checkdata(){
		if (this.product.getProductName().equals("") || this.product.getProductName()==null || 
				this.product.getDesc().equals("") || this.product.getDesc()==null ){
			
			FacesContext.getCurrentInstance().addMessage("error", 
					new FacesMessage("Error: fill the required filed first."));
			
			System.out.println("error in save ");
			return false;
		}
		return  true;
	}
    public void upload(FileUploadEvent event) {  

        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
             
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	public int getProdPK() {
		return prodPK;
	}


	public void setProdPK(int prodPK) {
		this.prodPK = prodPK;
	}


	public ProductService getProductService() {
		return productService;
	}


	public String edit(int id){
		prodPK=id;
		this.product.setId(id);
		this.product.setProductName(productService.findById(id).getProductName()); 
		this.product.setDesc(productService.findById(id).getDesc()); 
		this.product.setPrice(productService.findById(id).getPrice()); 
		this.product.setStock(productService.findById(id).getStock());
		this.selection=String.valueOf(productService.findById(id).getProductType().getId());
		
		return "/editProduct.jsf";
	}
	
	public String saveUpdate(){

		if (this.product.getProductName().equals("") || this.product.getProductName()==null || 
				this.product.getDesc().equals("") || this.product.getDesc()==null ){
			
        	return "/editProduct.jsf";
		}

		
		productService.update(this.product.getProductName(),  this.product.getDesc(),
				this.product.getPrice(), 
				productTypeService.findById(Integer.parseInt(selection)), 
				this.product.getStock() ,
				prodPK);
		
		return "/product.jsf";
	}
	

	public String saveNew(){
		
		if (this.product.getProductName().equals("") || this.product.getProductName()==null || 
				this.product.getDesc().equals("") || this.product.getDesc()==null ){
			
        	return "/newProduct.jsf";
		}
		
		productService.save(new Product(this.product.getProductName(),  this.product.getDesc(), 
				this.product.getPrice(), 
				productTypeService.findById(Integer.parseInt(selection)), 
				this.product.getStock()));
		
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
	
	public String getSelection() {
		return selection;
	}


	public void setSelection(String selection) {
		this.selection = selection;
	}



	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}


	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

}
