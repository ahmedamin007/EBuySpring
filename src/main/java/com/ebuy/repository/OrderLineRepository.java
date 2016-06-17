package com.ebuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebuy.model.Order;
import com.ebuy.model.Orderline;
import com.ebuy.model.Person;
import com.ebuy.model.Product;

@Repository
public interface OrderLineRepository extends JpaRepository<Orderline, Integer> {

	public List<Orderline> findByOrder(Order order);
	public Orderline findById(int id);
	@Query("select o from Orderline o where o.product = ?1 and o.order.person=?2 ")
	public List<Order> findByProductAndOrder(Product product,Person person);
}
