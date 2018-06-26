package com.takmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takmen.models.dao.EmpleadoRepository;
import com.takmen.models.entity.Empleado;
import com.takmen.models.entity.TipoEmpleado;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return (List<Empleado>)empleadoRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Empleado empleado) {
		// TODO Auto-generated method stub
		empleadoRepository.save(empleado);
	}

	@Override
	@Transactional(readOnly=true)
	public Empleado findById(Long id) {
		// TODO Auto-generated method stub
		return empleadoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		empleadoRepository.deleteById(id);		
	}

	@Override
	@Transactional(readOnly=true)
	public Empleado recuperarPorIdConOrden(Long id) {
		// TODO Auto-generated method stub
		return empleadoRepository.recuperarPorIdConOrdenes(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Empleado> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return empleadoRepository.findAll(pageable);
	}

	@Override
	public List<TipoEmpleado> tiposempleados() {
		// TODO Auto-generated method stub
		return empleadoRepository.tiposempleados();
	}

}
