package com.takmen.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.takmen.models.entity.Empleado;
import com.takmen.models.entity.TipoEmpleado;

@Repository
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long>{
	
	@Query("select e from Empleado e left join fetch e.ordenes o where e.idEmpleado=?1")
	public Empleado recuperarPorIdConOrdenes(Long id);
	
	@Query("Select t from TipoEmpleado t")
	public List<TipoEmpleado> tiposempleados();
}
