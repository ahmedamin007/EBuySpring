package com.ebuy.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebuy.model.Order;
import com.ebuy.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	public Order save(Order order){
		return orderRepository.save(order);
	}
	
	public void updateOrderCheckOut(boolean checkOutFlag , int id){
		if (orderRepository.findById(id).isCheckOutFlag()==false) {
			orderRepository.setOrderById(checkOutFlag, id);
			orderRepository.flush();
		}
		
	}
	public void delete(Order order){
		 orderRepository.delete(order);
		
	}
	
	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	
	public Order findById(int id){
		return orderRepository.findById(id);
	}
	
	public List<Order> findByDate(Date orderDate){
		return orderRepository.findByOrderDate(orderDate);
	}
	
	public List<Order> findByOrderDateOrCheckOutFlag (Date orderDate ,  boolean checkOutFlag){
		return orderRepository.findByOrderDateOrCheckOutFlag(orderDate, checkOutFlag);
	}
	
}
