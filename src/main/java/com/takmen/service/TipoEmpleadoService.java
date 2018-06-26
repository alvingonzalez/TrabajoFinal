package com.takmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takmen.models.dao.TipoEmpleadoRepository;
import com.takmen.models.entity.TipoEmpleado;

@Service
public class TipoEmpleadoService implements ITipoEmpleadoService {

	@Autowired
	private TipoEmpleadoRepository tipoempleadorepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoEmpleado> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoEmpleado>)tipoempleadorepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoEmpleado findById(Long id) {
		// TODO Auto-generated method stub
		return tipoempleadorepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		tipoempleadorepository.deleteById(id);
	}

	@Override
	@Transactional
	public void save(TipoEmpleado obj) {
		// TODO Auto-generated method stub
		tipoempleadorepository.save(obj);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TipoEmpleado> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return tipoempleadorepository.findAll(pageable);
	}

}
