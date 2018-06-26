package com.takmen.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class Empleado implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEmpleado;
	
	@NotNull
	private String nombreEmpleado;
	
	@NotNull
	private String apellidoEmpleado;
	
	@NotNull
	private String direccionEmpleado;
	
	@NotNull
	private int telefonoEmpleado;
	
	@NotNull
	@Email
	private String correoEmpleado;
	
	@OneToMany(mappedBy="empleado", fetch=FetchType.LAZY)
	private List<Orden> ordenes;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private TipoEmpleado tipoempleado;
	
	public TipoEmpleado getTipoempleado() {
		return tipoempleado;
	}

	public void setTipoempleado(TipoEmpleado tipoempleado) {
		this.tipoempleado = tipoempleado;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApellidoEmpleado() {
		return apellidoEmpleado;
	}

	public void setApellidoEmpleado(String apellidoEmpleado) {
		this.apellidoEmpleado = apellidoEmpleado;
	}

	public String getDireccionEmpleado() {
		return direccionEmpleado;
	}

	public void setDireccionEmpleado(String direccionEmpleado) {
		this.direccionEmpleado = direccionEmpleado;
	}

	public int getTelefonoEmpleado() {
		return telefonoEmpleado;
	}

	public void setTelefonoEmpleado(int telefonoEmpleado) {
		this.telefonoEmpleado = telefonoEmpleado;
	}

	public String getCorreoEmpleado() {
		return correoEmpleado;
	}

	public void setCorreoEmpleado(String correoEmpleado) {
		this.correoEmpleado = correoEmpleado;
	}

	public List<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	} 
	
	@Override
	public String toString() {
		
		return nombreEmpleado + " " +apellidoEmpleado; 
	}
	
}
