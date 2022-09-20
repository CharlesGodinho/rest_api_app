package com.example.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private PersonService service;
	
	@RequestMapping(value = "/id",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(
			@PathVariable(value = "id") String id) throws Exception{
	return service.findByid(id);	
	}
}