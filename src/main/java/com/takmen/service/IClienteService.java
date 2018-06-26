package com.takmen.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.takmen.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findById(Long id);
	
	public void deleteById(Long id);
	
	public Cliente recuperarPorIdConOrden(Long id);
	
	public Page<Cliente> findAll(Pageable pageable);
	
	
	

}
