package com.takmen.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCliente;
	
	@NotNull
	private String nombreCliente;
	
	@NotNull
	private String apellidoCliente;
	
	@NotNull
	private int dni;
	
	@NotNull
	private String nombreEmpresa;
	
	@NotNull
	@Email
	private String correoCliente;
	
	@NotNull
	private String rucEmpresa;
	
	@NotNull
	private String pais;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY , cascade=CascadeType.ALL)
	private List<Orden> ordenes; 
	
	
	public Cliente ()
	{
		ordenes=new ArrayList<>();
				
	}


	public Long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public String getApellidoCliente() {
		return apellidoCliente;
	}


	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getCorreoCliente() {
		return correoCliente;
	}


	public void setCorreoCliente(String coreoCliente) {
		this.correoCliente = coreoCliente;
	}


	public String getRucEmpresa() {
		return rucEmpresa;
	}


	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public List<Orden> getOrdenes() {
		return ordenes;
	}


	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}
	
	
	@Override
	public String toString()
	{
		return nombreCliente +" "+ apellidoCliente;
	}
	
	
	
	
}
