package com.ebuy.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebuy.service.MailService;
import com.ebuy.service.OrderLineService;

@Component
@ManagedBean
@SessionScoped
public class CheckOutBean implements Serializable{
 

	private static final long serialVersionUID = 1L;
	@Autowired
	 OrderLineService orderLineService;
	 
	 public CheckOutBean() {
		
	}
	public OrderLineService getOrderLineService() {
		return orderLineService;
	 }
	 public void setOrderLineService(OrderLineService orderLineService) {
		 this.orderLineService = orderLineService;
	 }
	 
	 public String processPayment() throws AddressException, MessagingException{
		  System.out.println("*********enter save 1");
		  orderLineService.processPayment();
		  System.out.println("*********finish save 2");
		  return "productList.xhtml";
	 }
 
 
 }