package com.takmen.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUD<T> {

	List<T>findAll();
	void save(T obj);
	T findById(Long id);	
	
	Page<T> findAll(Pageable pageable);
	void delete(Long id);
	
	
	
}
