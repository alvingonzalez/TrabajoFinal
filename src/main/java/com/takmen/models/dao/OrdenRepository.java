package com.takmen.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.takmen.models.entity.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long>{
/* 
 @Query("select o from Orden o "
			+ " join fetch o.cliente c"
			+ " join fetch o.empleado e"
			+ " join fetch o.detalleOP d"
			+ " join fetch d.prenda p "
			+ " join fetch p.tipoPrenda"
			+ " join fetch p.detalleServicioPrenda s "
			+ " join fetch s.servicio "
			+ " where o.idOrden=?1")
			*/
	@Query(value="select * from orden o inner join cliente c on o.cliente_id_cliente=c.id_cliente inner join empleado e " + 
			"on e.id_empleado=o.empleado_id_empleado inner join detalleop dop on dop.id_orden=o.id_orden inner join prenda pr " + 
			"on pr.id_prenda=dop.id_prenda inner join tipo_prenda t on t.id_tipo_prenda=pr.id_tipo_prenda inner join detalle_servicio_prenda d " + 
			"on d.id_prenda=pr.id_prenda inner join servicio s on s.id_servicio=d.id_servicio where o.id_orden=?1", nativeQuery=true)
	public Orden detalleFactura(Long id);

}
