package com.takmen.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.takmen.models.entity.Servicio;

public interface IServicioService {
	
	public List<Servicio> findBynombreServicioLikeIgnoreCase(String term);
	
	public List<Servicio> findAll();	
	public void save(Servicio servicio);
	public Servicio findByidServicio(Long id);
	
	//CRUD
	public void delete(Long id);
	public Page<Servicio> findAll(Pageable pageable);
}
