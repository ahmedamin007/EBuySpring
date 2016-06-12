package com.ebuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ebuy.model.Orderline;
import com.ebuy.repository.OrderLineRepository;

@Service
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
