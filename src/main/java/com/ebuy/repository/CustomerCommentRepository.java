package com.ebuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ebuy.model.CustomerComment;
import com.ebuy.model.Product;

@Repository
public interface CustomerCommentRepository  extends JpaRepository<CustomerComment, Integer> {


	public List<CustomerComment> findByProduct(Product product);
	

}
