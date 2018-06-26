package com.takmen.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.takmen.models.entity.TipoEmpleado;

@Repository
public interface TipoEmpleadoRepository extends JpaRepository<TipoEmpleado, Long> {

}
