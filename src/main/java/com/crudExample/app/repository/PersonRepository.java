package com.crudExample.app.repository;

import com.crudExample.app.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {


}

