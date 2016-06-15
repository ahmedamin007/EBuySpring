package com.ebuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ebuy.model.CustomerComment;
import com.ebuy.model.Product;
import com.ebuy.repository.CustomerCommentRepository;

@Service
public class CustomerCommentService {
	
	@Autowired
	CustomerCommentRepository commentRepository;
	
	public CustomerCommentService() {
		
	}
	
	public CustomerComment save(CustomerComment CustomerComment){
		return commentRepository.save(CustomerComment);
	}

	public List<CustomerComment> findAll(){
		return commentRepository.findAll();
	}

	public void delete(CustomerComment CustomerComment){
		commentRepository.delete(CustomerComment);
	}
	
	public List<CustomerComment> findByProduct(Product product){
		return commentRepository.findByProduct(product);
	}

}
