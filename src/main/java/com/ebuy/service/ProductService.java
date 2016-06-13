package com.ebuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebuy.model.Product;
import com.ebuy.model.ProductType;
import com.ebuy.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public ProductService() {
		// TODO Auto-generated constructor stub
	}
	
	public  Product  findById(int id){
		return productRepository.findById(id);
	}
	
	public void update(String productName, String desc, double price ,ProductType productType , int stock,  int id) {
		productRepository.setProductById(productName, desc, price, productType, stock, id);
	}	
	
	public List<Product> findAll(){
		return productRepository.findAll();
		
	}
	
	public Product save(Product product){
		return productRepository.save(product);
		
	}
	
	public void delete(Product product){
		productRepository.delete(product);
		
	}

}
