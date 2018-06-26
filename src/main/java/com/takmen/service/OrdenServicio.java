package com.takmen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takmen.models.dao.OrdenRepository;
import com.takmen.models.entity.Orden;

@Service
public class OrdenServicio implements IOrdenService {

	@Autowired
	private OrdenRepository ordenRepository;
	
	@Override
	@Transactional
	public void save(Orden orden) {
		// TODO Auto-generated method stub

		ordenRepository.save(orden);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		ordenRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Orden findById(Long id) {
		// TODO Auto-generated method stub
		return ordenRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Orden detalleFactura(Long id) {
		// TODO Auto-generated method stub
		return ordenRepository.detalleFactura(id);
	}


	

}
