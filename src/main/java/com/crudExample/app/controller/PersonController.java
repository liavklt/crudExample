package com.crudExample.app.controller;

import com.crudExample.app.model.Person;
import com.crudExample.app.service.PersonService;
import com.crudExample.app.util.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

	@Autowired
	PersonService personService;

	@RequestMapping(value="/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer id) {
		Person person = personService.findById(id);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPeople(){
		List<Person> people = personService.getAllPeople();
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

	}

	//TODO: Automatically increase id and check name
	@RequestMapping(value="/person/", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody Person person, UriComponentsBuilder builder){
	    if (personService.personExists(person))
                return new ResponseEntity(new CustomError(
                        "Unable to create. Person already exists."),
                        HttpStatus.CONFLICT);
	    personService.createPerson(person);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	

}
