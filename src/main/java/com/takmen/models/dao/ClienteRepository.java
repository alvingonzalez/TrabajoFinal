package com.takmen.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.takmen.models.entity.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>{

	@Query("select c from Cliente c left join fetch c.ordenes o where c.idCliente=?1")
	public Cliente recuperarPorIdConOrden(Long id);
	
}
