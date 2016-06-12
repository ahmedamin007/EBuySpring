package com.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebuy.model.Orderline;

@Repository
public interface OrderLineRepository extends JpaRepository<Orderline, Integer> {

}
