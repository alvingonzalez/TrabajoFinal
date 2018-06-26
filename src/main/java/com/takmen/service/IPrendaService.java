package com.takmen.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.takmen.models.entity.Prenda;

public interface IPrendaService {

	public List<Prenda> findByTipoPrendaIn(String arg);
	
	public  List<Prenda> findBynombrePrendaLikeIgnoreCase(String term);
	
	public List<Prenda> findAll();
	
	public void save(Prenda prenda);
	
	public Prenda findById(Long id);
	
	public void deleteById(Long id);
	
	public Page<Prenda> findAll(Pageable pageable);
}
