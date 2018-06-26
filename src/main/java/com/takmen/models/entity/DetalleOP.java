package com.takmen.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class DetalleOP implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDetalleOP;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPrenda")
	private Prenda prenda;
	
	@NotNull
	private Integer cantidad;

	public Long getIdDetalleOP() {
		return idDetalleOP;
	}

	public void setIdDetalleOP(Long idDetalleOP) {
		this.idDetalleOP = idDetalleOP;
	}

	public Prenda getPrenda() {
		return prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getMontoLinea()
	{
		return cantidad*prenda.getTotalPrenda();
	}
}

