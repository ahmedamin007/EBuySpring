package com.ebuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebuy.model.Order;
import com.ebuy.model.Orderline;
import com.ebuy.model.Person;
import com.ebuy.model.Product;
import com.ebuy.repository.OrderLineRepository;

@Service
@Transactional
public class OrderLineService {

	@Autowired
	OrderLineRepository orderLineRepository;
	
	
	public OrderLineService() {
	
	}
	
	public Orderline save(Orderline orderline){
		return orderLineRepository.save(orderline);
		
	}
	
	public void delete(Orderline orderline){
		orderLineRepository.delete(orderline);
	}
	
	public List<Orderline> findByOrder(Order order){
		return orderLineRepository.findByOrder(order);
	}
	
	public Orderline findById(int id){
		return orderLineRepository.findById(id);
	}
	
	public  List<Order> findByProductAndOrder(Product product,Person person){
		return orderLineRepository.findByProductAndOrder(product, person);
	}

}
