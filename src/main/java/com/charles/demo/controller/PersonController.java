package com.charles.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charles.demo.data.vo.v1.PersonVO;
import com.charles.demo.services.PersonService;
import com.charles.demo.util.MediaType;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findByid(id);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML,
			MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML,
					MediaType.APPLICATION_YML })
	public PersonVO create(@RequestBody PersonVO PersonVO) {
		return service.create(PersonVO);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML,
			MediaType.APPLICATION_YML }, consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML,
					MediaType.APPLICATION_YML })
	public PersonVO update(@RequestBody PersonVO PersonVO) {
		return service.update(PersonVO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML, MediaType.APPLICATION_YML })
	public List<PersonVO> findByAll() {
		return service.findAll();
	}
}