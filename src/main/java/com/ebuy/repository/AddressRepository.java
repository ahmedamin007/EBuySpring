package com.ebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebuy.model.Address;;

public interface AddressRepository extends JpaRepository<Address,Integer> {
	
}
