package com.crudExample.app.controller;

import com.crudExample.app.model.Person;
import com.crudExample.app.service.PersonService;
import com.crudExample.app.util.CustomError;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/people")
public class PersonController {

  @Autowired
  PersonService personService;
  private static Validator validator;

  @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
  public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer id) {
    Person person = personService.findById(id);
    return new ResponseEntity<Person>(person, HttpStatus.OK);
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity<List<Person>> getAllPeople() {
    List<Person> people = personService.getAllPeople();
    return new ResponseEntity<List<Person>>(people, HttpStatus.OK);

  }

  @RequestMapping(value = "/person/", method = RequestMethod.POST)
  public ResponseEntity<?> createPerson(@RequestBody Person person, UriComponentsBuilder builder) {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
    Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
    for (ConstraintViolation constraintViolation : constraintViolations) {
      return new ResponseEntity(
          new CustomError(constraintViolation.getPropertyPath().toString() + " " +
              constraintViolation.getMessage()), HttpStatus.BAD_REQUEST);
    }
    if (personService.personExists(person)) {
      return new ResponseEntity(new CustomError(
          "Unable to create. Person already exists."),
          HttpStatus.CONFLICT);
    }

    personService.createPerson(person);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getId()).toUri());
    return new ResponseEntity<String>(headers, HttpStatus.CREATED);
  }


}
