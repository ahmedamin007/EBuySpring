package com.ebuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebuy.model.Orderline;
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

}
