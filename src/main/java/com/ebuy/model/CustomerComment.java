package com.ebuy.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// Ahmed Amin

@Entity
@Table(name="CustomerComment")
public class CustomerComment {

	@Id @GeneratedValue
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;
	@OneToOne
	private Person person;
	@OneToOne
	private Product product;
	private Integer rating;
	private String commentText;
	
	public CustomerComment() {

	}
	
	public CustomerComment(Date commentDate, Person person, Product product, Integer rating, String commentText) {
		super();
		this.commentDate = commentDate;
		this.person = person;
		this.product = product;
		this.rating = rating;
		this.commentText = commentText;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getCommentDate() {
		return commentDate;
	}


	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Integer getRating() {
		return rating;
	}


	public void setRating(Integer rating) {
		this.rating = rating;
	}


	public String getCommentText() {
		return commentText;
	}


	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}




}
