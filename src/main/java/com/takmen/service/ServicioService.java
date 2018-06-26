package com.takmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takmen.models.dao.ServicioRepository;
import com.takmen.models.entity.Servicio;

@Service
public class ServicioService implements IServicioService {

	@Autowired
	private ServicioRepository servicioRepository;

	@Override
	@Transactional
	public void save(Servicio servicio) {
		// TODO Auto-generated method stub
		servicioRepository.save(servicio);

	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Servicio> findAll() {
		// TODO Auto-generated method stub
		return (List<Servicio>)servicioRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Servicio> findBynombreServicioLikeIgnoreCase(String term) {
		// TODO Auto-generated method stub
		return servicioRepository.findBynombreServicioLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional(readOnly=true)
	public Servicio findByidServicio(Long id) {
		// TODO Auto-generated method stub
		return servicioRepository.findByidServicio(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		servicioRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Servicio> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return servicioRepository.findAll(pageable);
	}


}
