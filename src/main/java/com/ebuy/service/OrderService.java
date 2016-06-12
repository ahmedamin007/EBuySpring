package com.ebuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebuy.model.Order;
import com.ebuy.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	public Order save(Order order){
		return orderRepository.save(order);
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
	
	
}
