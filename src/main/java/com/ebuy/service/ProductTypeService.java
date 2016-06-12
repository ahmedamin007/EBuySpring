package com.ebuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ebuy.model.ProductType;
import com.ebuy.repository.ProductTypeRepository;

@Service
public class ProductTypeService {

	@Autowired
	ProductTypeRepository productTypeRepository ; 
	
	
	public ProductTypeService() {
		
	}
	
	public ProductType save(ProductType productType){
		return productTypeRepository.save(productType);
		
	}
	
	public void delete(ProductType productType){
		productTypeRepository.delete(productType);
		
	}

}
