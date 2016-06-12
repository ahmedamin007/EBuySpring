
package com.ebuy.service;


import com.ebuy.model.Person;
import com.ebuy.repository.PersonRepository;
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
    
	public PersonService () {
        super();
        
    }
    
    public Person save(Person person){
    	System.out.println("enter save name  " + person.getName());
    	System.out.println("enter save username " + person.getPassword());
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
	
}
