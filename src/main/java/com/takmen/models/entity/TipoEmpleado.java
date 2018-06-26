package com.takmen.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tipoempleado")
public class TipoEmpleado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtipoempleado;
	
	@NotNull
	private String tipoempleado;

	public Long getIdtipoempleado() {
		return idtipoempleado;
	}

	public void setIdtipoempleado(Long idtipoempleado) {
		this.idtipoempleado = idtipoempleado;
	}

	public String getTipoempleado() {
		return tipoempleado;
	}

	public void setTipoempleado(String tipoempleado) {
		this.tipoempleado = tipoempleado;
	}
	
}
