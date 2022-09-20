package com.example.demo.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.demo.models.Person;

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
}
