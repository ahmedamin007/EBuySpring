package com.ebuy.bean;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;


import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import com.ebuy.model.Address;
import com.ebuy.model.CustomerComment;
import com.ebuy.model.Person;
import com.ebuy.model.Product;
import com.ebuy.model.ProductType;
import com.ebuy.service.CustomerCommentService;
import com.ebuy.service.OrderLineService;
import com.ebuy.service.PersonService;
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

	@Autowired
	CustomerCommentService commentService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	OrderLineService orderLineService;
	
	List<CustomerComment> CustomerCommentList=new ArrayList<>();
	
	
	private String currentProductName;
	private int currentProductId;
	private String currentProductDesc;
	private String userComment;
	private Integer rating;
	private String message;
	

	public String getCurrentProductName() {
		return currentProductName;
	}

	public void setCurrentProductName(String currentProductName) {
		this.currentProductName = currentProductName;
	}

	public int getCurrentProductId() {
		return currentProductId;
	}

	public void setCurrentProductId(int currentProductId) {
		this.currentProductId = currentProductId;
	}

	public String getCurrentProductDesc() {
		return currentProductDesc;
	}

	public void setCurrentProductDesc(String currentProductDesc) {
		this.currentProductDesc = currentProductDesc;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
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

	public CustomerCommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CustomerCommentService commentService) {
		this.commentService = commentService;
	}

	public List<CustomerComment> getCustomerCommentList() {
		return CustomerCommentList;
	}

	public void setCustomerCommentList(List<CustomerComment> customerCommentList) {
		CustomerCommentList = customerCommentList;
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

	
	
	/////////////Zaliem
	
	public String listProduct(){
		productService.findAll();
		return "productList.xhtml";
	}
	public String  addPro(){
		System.out.println("1");
		List<Address> address = new ArrayList<>();
		ProductType Computer=new ProductType("Computer","this is dell computer");
		Product newProduct= new Product("Hp","this is dell computer",15.6,Computer,6);
		Product newProduct1= new Product("Dell","this is dell computer",15.6,Computer,6);
		ProductType TV=new ProductType("TV","this is tele");
		Product newProduct2= new Product("tv","this is tv",50.4,TV,6);
		Product newProduct3= new Product("SamTv","this is tv",75.3,TV,6);
		ProductType Clothes=new ProductType("CL1","This is cloth");
		Product newProduct4= new Product("cl1","This is cloth",15.8,Clothes,8);
		Product newProduct5= new Product("cl2","This is cloth",23.5,Clothes,7);
		Product newProduct6= new Product("cl3","This is cloth",35.2,Clothes,9);
		ProductType Shoes=new ProductType("Shoes","This is Shoe type");
		Product newProduct7= new Product("shoe1","This is shoe",15.8,Shoes,8);
		Product newProduct8= new Product("shoe2","This is shoe",23.5,Shoes,7);
		Product newProduct9= new Product("shoe3","This is shoe",35.2,Shoes,9);
		Product newProduct10= new Product("shoe4","This is shoe",35.2,Shoes,9);
		System.out.println("2");
	   productTypeService.save(TV);
	   productTypeService.save(Computer);
	   productTypeService.save(Clothes);
	   productTypeService.save(Shoes);
	   productService.save(newProduct1);
		productService.save(newProduct);
		productService.save(newProduct2);
		productService.save(newProduct3);
		productService.save(newProduct4);
		productService.save(newProduct5);
		productService.save(newProduct6);
		productService.save(newProduct7);
		productService.save(newProduct8);
		productService.save(newProduct9);
		productService.save(newProduct10);
		System.out.println("3");
		return "faces/newjsf.xhtml";
	}
  public String proByType(){
      	productService.findByProductType_id(41);
    return "computerList.xhtml";
	//  return "productList.xhtml";
  }
  public String proByTypeClo(){
	  
    	productService.findByProductType_id(42);
  return "computerList.xhtml";
	//  return "productList.xhtml";
}
  public String proByTypeShoe(){
	  
    	productService.findByProductType_id(43);
  return "computerList.xhtml";
	//  return "productList.xhtml";
}
  public String proByTypeTv(){
	    productService.findByProductType_id(40);
	    return "computerList.xhtml";
		//  return "productList.xhtml";
	  }
  public String proDesc(int id,String type){
     product=productService.findById(id);
        if(type.equals("Computer")){
        	productService.findByProductType_id(41);
        }
        else if(type.equals("Tv")){
        	productService.findByProductType_id(40);
        }
        else if(type.equals("Clothes")){
        	productService.findByProductType_id(42);
        }
        else if(type.equals("Shoes")){
        	productService.findByProductType_id(43);
        }
        
        

		currentProductId=id;
		currentProductName=productService.findById(id).getProductName();
		currentProductDesc=productService.findById(id).getDesc();
		
		CustomerCommentList= commentService.findByProduct(productService.findById(id));
		
	    
        //#{customerCommentBean.findByProductViewOnly(prod.id)}
	  return "custProductDetail.xhtml";
  }
  public Product findById(){
	  return product;
  }
	
  
  ////////////ahmed
  public void validateComment(FacesContext context, UIComponent comp, Object value) {

	  
      String mno = (String) value;

		FacesContext facesContext = FacesContext.getCurrentInstance();
	    ELContext elContext = facesContext.getELContext();
	    ValueExpression targetExpression =
	        facesContext.getApplication().getExpressionFactory().createValueExpression(elContext, "#{loginBean.username}", Object.class);
		
	    String currentUserName=targetExpression.getValue(elContext).toString();
      Person person = personService.findByUserName(currentUserName);
      
      int count =0;
      try{
      	count=orderLineService.findByProductAndOrder(productService.findById(currentProductId), person).size();
      }
      catch (InvalidDataAccessApiUsageException ex) {
      	count=1;
      }
      catch (Exception exx){
      	count=0;
      }
      
		if ( count == 0 ){
          ((UIInput) comp).setValid(false);
          
          FacesMessage message = new FacesMessage(
                  "Sorry you can't add comment while you did not purchase this product !!");
          context.addMessage(comp.getClientId(context), message);
		}
		
      if (mno.length() < 4) {
          ((UIInput) comp).setValid(false);
          FacesMessage message = new FacesMessage(
                  "Minimum length of comment number is 4");
          context.addMessage(comp.getClientId(context), message);
      }
      
      if (mno.length() > 250 ) {
          ((UIInput) comp).setValid(false);
          FacesMessage message = new FacesMessage(
                  "Maximum length of comment number is 250");
          context.addMessage(comp.getClientId(context), message);
      }
  }
  
	public String saveUserComent(){
		System.out.println("enter save comment 1");
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    ELContext elContext = facesContext.getELContext();
	    ValueExpression targetExpression =
	        facesContext.getApplication().getExpressionFactory().createValueExpression(elContext, "#{loginBean.username}", Object.class);
		
	    String currentUserName=targetExpression.getValue(elContext).toString();
		
		if (currentUserName==null){
			return "/login.jsf";
		}
		
		Person person = personService.findByUserName(currentUserName);
		if (person ==null){
			return "/login.jsf";
		}


		CustomerComment newCustComment = new CustomerComment(new Date(), person , 
				productService.findById(currentProductId), this.rating, this.userComment);
		
		commentService.save(newCustComment);
		CustomerCommentList= commentService.findByProduct(productService.findById(currentProductId));
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful",  "Successful Saved... " ) );
		this.userComment="";
		this.rating=0;

		return "/custProductDetail.jsf";
	}

  //////////////ahmed end
	
}
