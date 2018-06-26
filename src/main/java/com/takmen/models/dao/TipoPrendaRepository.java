package com.takmen.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.takmen.models.entity.TipoPrenda;

@Repository
public interface TipoPrendaRepository extends JpaRepository<TipoPrenda, Long> {

	@Query("SELECT t FROM  TipoPrenda t")
	public List<TipoPrenda> tiposPrenda();
	
	public List<TipoPrenda> findAll();
}
