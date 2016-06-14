package com.ebuy.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebuy.model.Person;
import com.ebuy.service.PersonService;

@Component
@ManagedBean
@RequestScoped
public class customerBean {

	@Autowired
	PersonService personService;
	
	
	public customerBean() {
		
	}
	
	public List<Person> findAll(){
		return personService.findAll();
	}

}
