package com.ebuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebuy.model.Product;
import com.ebuy.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public ProductService() {
		// TODO Auto-generated constructor stub
	}
	
	public Product save(Product product){
		return productRepository.save(product);
		
	}
	
	public void delete(Product product){
		productRepository.delete(product);
		
	}

}