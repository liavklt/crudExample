package com.crudExample.app.service;

import com.crudExample.app.model.Person;
import com.crudExample.app.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;


  private static List<Person> people;


  @Override
  public Person findById(int id) {
    return personRepository.findOne(id);

  }


  @Override
  public List<Person> getAllPeople() {
    people = new ArrayList<>();
    for (Person person : personRepository.findAll()) {
      people.add(person);
    }
    return people;
  }

  @Override
  public boolean personExists(Person person) {
    return findByName(person.getLastName(), person.getFirstName());
  }

  private boolean findByName(String lastName, String firstName) {

    boolean found = false;
    for (Person p : getAllPeople()) {
      if (p.getLastName().equalsIgnoreCase(lastName) &&
          p.getFirstName().equalsIgnoreCase(firstName)) {
        found = true;
        break;
      }


    }
    return found;


  }

  @Override
  public void createPerson(Person person) {

    Integer count = getAllPeople().size();
    person.setId(count + 1);
    personRepository.save(person);
  }

}
