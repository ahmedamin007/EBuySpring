package com.ebuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebuy.model.ProductType;
import com.ebuy.repository.ProductTypeRepository;

@Service
@Transactional
public class ProductTypeService {

	@Autowired
	ProductTypeRepository productTypeRepository ; 
	
	
	public ProductTypeService() {
		
	}
	
	public  ProductType  findById(int id){
		return productTypeRepository.findById(id);
		
	}
	public void update(String name, String desc, int id) {
		productTypeRepository.setProductTypeById(name, desc, id); 
	}	
	
	public List<ProductType> findAll(){
		return productTypeRepository.findAll();
		
	}
	public ProductType save(ProductType productType){
		return productTypeRepository.save(productType);
		
	}
	
	public void delete(ProductType productType){
		productTypeRepository.delete(productType);
	}
	


}
