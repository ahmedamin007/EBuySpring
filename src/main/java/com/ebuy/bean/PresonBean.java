package com.ebuy.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ebuy.model.EGender;
import com.ebuy.model.Person;
import com.ebuy.model.Address;
import com.ebuy.service.PersonService;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component
@ManagedBean
@SessionScoped
public class PresonBean implements Serializable  {

	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	PersonService personService;
	
	public PresonBean(PersonService personService ) {
		this.personService=personService;
	}
	
	public PresonBean() {
		
	}
	
	public PersonService getPersonService() {
		return personService;
	}
	
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public String  addCust(){
		System.out.println("1");
		List<Address> address = new ArrayList<>();
//		address.add(new Address("alex home" , "iwoa" ));
//		address.add(new Address("crio home" , "tanta office" ));
//		
//		Person newPerson =new Person( "ahmed", "ahmed", "amin", "8988888", EGender.Male,address);
		System.out.println("2");
		//personService.save(newPerson);
		System.out.println("3");
		return "faces/newjsf.xhtml";
	}

}
