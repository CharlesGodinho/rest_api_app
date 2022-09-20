package com.charles.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.charles.demo.models.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public Person findByid(String id) {
		logger.info("Finding new Person");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setName("nome da pessoa");
		person.setAddress("Rua dos bobo N 0");
		person.setGender("no-binary");
		return person;
	}

	public Person create(Person person) {
		logger.info("Create one Person");
		return person;
	}

	public Person update(Person person) {
		logger.info("Updating one Person");
		return person;
	}

	public void delete(String id) {
		logger.info("delete one Person");
	}

	public Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setName("nome da pessoa" + i);
		person.setAddress("Rua dos bobo N 0");
		person.setGender("no-binary");
		return person;
	}

	public List<Person> findAll() {
		ArrayList<Person> persons = new ArrayList<>();
		logger.info("Finding all people");
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}
}
