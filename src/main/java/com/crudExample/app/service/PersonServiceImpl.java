package com.crudExample.app.service;

import com.crudExample.app.model.Person;
import com.crudExample.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRepository personRepository;
	
	@Override
	public Person findById(int id) {
	    return personRepository.findOne(id);

	}


	@Override
	public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        for (Person person : personRepository.findAll())
            people.add(person);
		return people;
	}
	@Override
    public boolean personExists(Person person){
	    return personRepository.exists(person.getId());
    }

    @Override
    public void createPerson(Person person){
        personRepository.save(person);
    }

}
