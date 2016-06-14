package com.ebuy.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebuy.model.Order;
import com.ebuy.model.Orderline;
import com.ebuy.model.Product;
import com.ebuy.service.OrderLineService;
import com.ebuy.service.OrderService;

@Component
@ManagedBean
@RequestScoped
public class orderBean {

	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderLineService orderLineService;
	
	List<Orderline> orderLineList=new ArrayList<>();
	List<Order> orderList=new ArrayList<>();
	private Date date;
	private boolean checkOut;
	private boolean selectAll;
	

	public orderBean () {
		
	}
	
	public String updateCheckOut(){
        for (Order ord: orderList){
        	System.out.println("before update ...." + ord.isCheckOutFlag() + " "  + ord.getId());
        	orderService.updateOrderCheckOut(ord.isCheckOutFlag(), ord.getId());
        }
		return "/orderDetails.jsf";
	}

	public List<Orderline> findOrderLineByOrder(int id){
		return orderLineService.findByOrder(orderService.findById(id));
	}
	
	public String openOrderLine(int id){
		orderLineList=findOrderLineByOrder(id);
		return "/orderDetails.jsf";
	}

	public String deleteOrder(int id) {
		orderService.delete(orderService.findById(id));
		return "/orderView.jsf";
	}
	
	public String deleteOrderDetail(int id) {
		orderLineService.delete(orderLineService.findById(id));
		return "/orderView.jsf";
	}
	
	
	public String findOrderByDate(){
		orderList=orderService.findByOrderDateOrCheckOutFlag(this.date, this.checkOut);
		
		return "/orderView.jsf";
	}
	
    public void addMessage() {
        String summary = checkOut ? "All Checked Order" : "Current pendding orders";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
    
    public void selectMessage() {
        String summary = selectAll ? "Checkout All Orders" : "Deselect All Orders";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
        for (int i=0;i<orderList.size() ;i++){
        	orderList.get(i).setCheckOutFlag(this.selectAll);
        }
    }
    
	public List<Order> getAllOrder() {
		return orderService.findAll();
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	public boolean isSelectAll() {
		return selectAll;
	}



	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}



	public boolean isCheckOut() {
		return checkOut;
	}



	public void setCheckOut(boolean checkOut) {
		this.checkOut = checkOut;
	}

	public OrderLineService getOrderLineService() {
		return orderLineService;
	}

	public void setOrderLineService(OrderLineService orderLineService) {
		this.orderLineService = orderLineService;
	}

	public List<Orderline> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<Orderline> orderLineList) {
		this.orderLineList = orderLineList;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
