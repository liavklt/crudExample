package com.crudExample.app.model;

import javax.persistence.*;

@Entity
@Table(name="Person")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@Column(name="first_name")
	private String first_name;
	@Column(name="last_name")
	private String last_name;
	@Column(name="age")
	private int age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String lastName) {
		this.last_name = lastName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + first_name + ", lastName=" + last_name + ", age=" + age + "]";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
