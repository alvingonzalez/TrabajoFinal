package com.takmen.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.takmen.models.entity.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long>{

	public List<Servicio> findBynombreServicioLikeIgnoreCase(String term);
		
    public List<Servicio> findAll();
	
	@Query("SELECT s FROM  Servicio s")
	public List<Servicio> tiposServicio();
	
	public Servicio findByidServicio(Long id);
}
