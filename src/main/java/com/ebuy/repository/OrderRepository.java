package com.ebuy.repository;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ebuy.model.Order;
import com.ebuy.model.ProductType;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	public Order findById(int id);
	public List<Order> findByOrderDate(Date orderDate);
	
	@Query("select u from Order u where (u.orderDate = :orderDate or :orderDate is null) "
										+ "	and (u.checkOutFlag = :checkOutFlag ) ")
	List<Order> findByOrderDateOrCheckOutFlag(@Param("orderDate")  Date orderDate , @Param("checkOutFlag") boolean checkOutFlag);

	
	@Modifying
	@Query("update Order u set u.checkOutFlag = ?1 where u.id = ?2")
	public void setOrderById(boolean checkOutFlag, int id);
	
}
