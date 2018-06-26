package com.takmen.service;

import com.takmen.models.entity.Orden;

public interface IOrdenService {

	public void save(Orden orden);

	public void deleteById(Long id);

	public Orden findById(Long id);

	public Orden detalleFactura(Long id);

}
