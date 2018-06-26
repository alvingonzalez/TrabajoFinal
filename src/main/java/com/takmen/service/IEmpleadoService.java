package com.takmen.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.takmen.models.entity.Empleado;
import com.takmen.models.entity.TipoEmpleado;

public interface IEmpleadoService {
	
	public List<Empleado> findAll();
	
	public void save(Empleado empleado);
	
	public Empleado findById(Long id);
	
	public void deleteById(Long id);
	
	public Empleado recuperarPorIdConOrden(Long id);
	
	public Page<Empleado> findAll(Pageable pageable);
	
	public List<TipoEmpleado>tiposempleados();

}
