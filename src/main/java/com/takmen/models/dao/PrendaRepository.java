package com.takmen.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.takmen.models.entity.Prenda;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda, Long>{
	
	@Query("select p from Prenda p join fetch p.tipoPrenda t join fetch p.detalleServicioPrenda d join fetch d.servicio s where t.nombrePrenda like %?1% ")
	public List<Prenda> findByTipoPrendaIn(String arg);

	public  List<Prenda> findBynombrePrendaLikeIgnoreCase(String term);
	
	
}