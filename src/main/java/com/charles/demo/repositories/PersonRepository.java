package com.charles.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.charles.demo.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	@Modifying
	@Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
	void disablePerson(@Param("id") Long id);
	
	@Query("SELECT p FROM Person p WHERE p.name LIKE LOWER(CONCAT ('%',:name,'%'))")
	Page<Person> findPersonsByName(@Param("name") String name, Pageable pageable);
}