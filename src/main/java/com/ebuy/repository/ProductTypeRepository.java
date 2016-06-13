package com.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.ebuy.model.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
	
	@Modifying
	@Query("update ProductType u set u.name = ?1, u.desc = ?2  where u.id = ?3")
	public void setProductTypeById(String name, String desc, int id);
	public ProductType findByName(String name); 
	public ProductType findById(int id);
	
}
