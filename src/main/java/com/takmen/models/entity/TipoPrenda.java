package com.takmen.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TipoPrenda implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTipoPrenda;
	
	@NotNull
	private String nombrePrenda;

	public Long getIdTipoPrenda() {
		return idTipoPrenda;
	}

	public void setIdTipoPrenda(Long idTipoPrenda) {
		this.idTipoPrenda = idTipoPrenda;
	}

	public String getNombrePrenda() {
		return nombrePrenda;
	}

	public void setNombrePrenda(String nombrePrenda) {
		this.nombrePrenda = nombrePrenda;
	}
	
	
	
}
