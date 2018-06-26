package com.takmen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takmen.models.dao.PrendaRepository;
import com.takmen.models.entity.DetalleServicioPrenda;
import com.takmen.models.entity.Prenda;

@Service
public class PrendaService implements IPrendaService{

	@Autowired
	private PrendaRepository prendaRepository;
		
	@Override
	@Transactional(readOnly=true)
	public List<Prenda> findByTipoPrendaIn(String arg) {
		// TODO Auto-generated method stub
		return prendaRepository.findByTipoPrendaIn(arg);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Prenda> findBynombrePrendaLikeIgnoreCase(String term) {
		// TODO Auto-generated method stub
		return prendaRepository.findBynombrePrendaLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional(readOnly=true)
	public List<Prenda> findAll() {
		// TODO Auto-generated method stub
		return (List<Prenda>)prendaRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Prenda prenda) {
		// TODO Auto-generated method stub
		List<DetalleServicioPrenda> detalle = prenda.getDetalleServicioPrenda();
		
		for (DetalleServicioPrenda detalleServicioPrenda : detalle) {
			
			prenda.setPrecioPrenda(detalleServicioPrenda.getServicio().getPrecioServicio());
		}
		
				
		prendaRepository.save(prenda);
	}

	@Override
	@Transactional(readOnly=true)
	public Prenda findById(Long id) {
		// TODO Auto-generated method stub
		return prendaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		prendaRepository.deleteById(id);
	}

	@Override
	public Page<Prenda> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return prendaRepository.findAll(pageable);
	}

}
