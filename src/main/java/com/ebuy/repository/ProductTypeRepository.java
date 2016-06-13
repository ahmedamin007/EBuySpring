package com.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ebuy.model.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
	
	public ProductType findByName(String name); 
	
}
