
package com.ebuy.service;


import com.ebuy.model.Person;
import com.ebuy.repository.AddressRepository;
import com.ebuy.repository.PersonRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ahmedamin 
 */

@Service
@Transactional
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    AddressRepository addressRepository;
    
    
	public PersonService () {
        super();
        
    }
    
    public Person save(Person person){
    	addressRepository.save(person.getAddress());
        return personRepository.save(person);
    }
    
    public void delete (Person person ){
       // my update
        personRepository.delete(person);
    }

    public Person findByUserName(String username){
        return personRepository.findByUsername(username);
    }
    
    public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Person> findAll(){
		return this.personRepository.findAll();
	}
	
}
