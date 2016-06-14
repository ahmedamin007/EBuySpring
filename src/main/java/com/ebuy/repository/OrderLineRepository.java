package com.ebuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebuy.model.Order;
import com.ebuy.model.Orderline;

@Repository
public interface OrderLineRepository extends JpaRepository<Orderline, Integer> {

	public List<Orderline> findByOrder(Order order);
	public Orderline findById(int id);
}
