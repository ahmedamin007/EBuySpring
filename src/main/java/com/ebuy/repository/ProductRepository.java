package com.ebuy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebuy.model.Product;
import com.ebuy.model.ProductType;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

	@Modifying
	@Query("update Product u set u.productName = ?1, u.desc = ?2 , u.price = ?3, u.productType = ?4,u.stock = ?5 where u.id = ?6")
	public void setProductById(String productName, String desc,  double price ,ProductType productType , int stock,  int id);
	public Product findByProductName(String productName); 
	public Product findById(int id);
	
	//
}
