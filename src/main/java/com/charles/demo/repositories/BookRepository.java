package com.charles.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charles.demo.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}