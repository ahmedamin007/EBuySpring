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
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import com.ebuy.model.CustomerComment;
import com.ebuy.model.Person;
import com.ebuy.service.CustomerCommentService;
import com.ebuy.service.OrderLineService;
import com.ebuy.service.PersonService;
import com.ebuy.service.ProductService;

import javax.faces.component.UIInput;

@Component
@ManagedBean
@RequestScoped
public class customerCommentBean {

	@Autowired
	CustomerCommentService commentService;
	
	@Autowired
	ProductService productService;
	
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
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public customerCommentBean() {
		
	}
	
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

		return "/addComment.jsf";
	}
	
	public String findByProductViewOnly(int prodId) {
		currentProductId=prodId;
		currentProductName=productService.findById(prodId).getProductName();
		currentProductDesc=productService.findById(prodId).getDesc();
		
		CustomerCommentList= commentService.findByProduct(productService.findById(prodId));
		return "/viewCustComment.jsf";
	}

	public String findByProductReadWrite(int prodId) {
		currentProductId=prodId;
		currentProductName=productService.findById(prodId).getProductName();
		currentProductDesc=productService.findById(prodId).getDesc();
		this.userComment="";
		this.rating=0;
		
		CustomerCommentList= commentService.findByProduct(productService.findById(prodId));
		return "/addComment.jsf";
	}
	
	public int getCommentCount(int prodId){
		return commentService.findByProduct(productService.findById(prodId)).size();
	}
	public List<CustomerComment> getCustomerCommentList() {
		return CustomerCommentList;
	}

	public void setCustomerCommentList(List<CustomerComment> customerCommentList) {
		CustomerCommentList = customerCommentList;
	}

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

}
