package com.crudExample.app.service;

import com.crudExample.app.model.Person;
import java.util.List;

public interface PersonService {

  Person findById(int id);

  List<Person> getAllPeople();

  boolean personExists(Person person);

  void createPerson(Person person);
}
