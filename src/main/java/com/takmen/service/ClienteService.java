package com.takmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takmen.models.dao.ClienteRepository;
import com.takmen.models.entity.Cliente;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);

	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);

	}

	@Override
	@Transactional(readOnly=true)
	public Cliente recuperarPorIdConOrden(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.recuperarPorIdConOrden(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Cliente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteRepository.findAll(pageable);
	}

}
