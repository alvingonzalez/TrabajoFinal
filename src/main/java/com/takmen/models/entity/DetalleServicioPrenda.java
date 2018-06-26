package com.takmen.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class DetalleServicioPrenda implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDetalleServicioPrenda;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idServicio")
	private Servicio servicio;
	

	public Long getIdDetalleServicioPrenda() {
		return idDetalleServicioPrenda;
	}

	public void setIdDetalleServicioPrenda(Long idDetalleServicioPrenda) {
		this.idDetalleServicioPrenda = idDetalleServicioPrenda;
	}
	
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	
}
