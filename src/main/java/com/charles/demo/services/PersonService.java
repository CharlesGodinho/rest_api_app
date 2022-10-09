package com.charles.demo.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charles.demo.data.vo.PersonVO;
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
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		logger.info("creating one Person");
		Person entity = DozerMapper.parseObject(person, Person.class);
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}

	public PersonVO update(PersonVO person) {
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Sem registros"));
		entity.setName(person.getName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}

	public void delete(Long id) {
		logger.info("delete one Person");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem registros"));
		repository.delete(entity);
	}

	public List<PersonVO> findAll() {
		logger.info("Finding all people");
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
}
