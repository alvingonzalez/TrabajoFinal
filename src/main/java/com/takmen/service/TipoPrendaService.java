package com.takmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takmen.models.dao.TipoPrendaRepository;
import com.takmen.models.entity.TipoPrenda;

@Service
public class TipoPrendaService implements ITipoPrendaService {

	@Autowired
	private TipoPrendaRepository tipoPrendaRepository;

	@Override
	public List<TipoPrenda> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoPrenda>)tipoPrendaRepository.findAll();
	}

	@Override
	public void save(TipoPrenda obj) {
		// TODO Auto-generated method stub
		tipoPrendaRepository.save(obj);
	}

	@Override
	public TipoPrenda findById(Long id) {
		// TODO Auto-generated method stub
		return tipoPrendaRepository.findById(id).orElse(null);
	}

	@Override
	public Page<TipoPrenda> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return tipoPrendaRepository.findAll(pageable);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		tipoPrendaRepository.deleteById(id);
	}
	
	


}
