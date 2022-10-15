package com.charles.demo.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charles.demo.controller.PersonController;
import com.charles.demo.data.vo.v1.PersonVO;
import com.charles.demo.exceptions.RequiredObjectisNullException;
import com.charles.demo.exceptions.ResourceNotFoundException;
import com.charles.demo.mapper.DozerMapper;
import com.charles.demo.models.Person;
import com.charles.demo.repositories.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	PersonRepository repository;

	public PersonVO findByid(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem registros"));
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}

	public PersonVO create(PersonVO person) {
		logger.info("creating one Person");
		if (person == null) throw new RequiredObjectisNullException();

		Person entity = DozerMapper.parseObject(person, Person.class);
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public PersonVO update(PersonVO person) {

		if(person == null) throw new RequiredObjectisNullException();

		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Sem registros"));
		entity.setName(person.getName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		logger.info("delete one Person");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem registros"));
		repository.delete(entity);
	}

	public List<PersonVO> findAll() {
		logger.info("Finding all people");
		List<PersonVO> persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream()
				.forEach(p -> 
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}
}
