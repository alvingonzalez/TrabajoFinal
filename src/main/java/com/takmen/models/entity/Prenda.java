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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Prenda implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPrenda;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idTipoPrenda")
	private TipoPrenda tipoPrenda;
	
	@NotNull
	private String marcaPrenda;
	
	@NotNull
	private String caracteristicasPrenda;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="idPrenda")
	private List<DetalleServicioPrenda> detalleServicioPrenda;

	@NotNull
	private String nombrePrenda;
	
	@NotNull
	private double precioPrenda;
	
	
	public double getPrecioPrenda() {
		return precioPrenda;
	}

	public void setPrecioPrenda(double precioPrenda) {
		this.precioPrenda = precioPrenda;
	}

	public String getNombrePrenda() {
		return nombrePrenda;
	}

	public void setNombrePrenda(String nombrePrenda) {
		this.nombrePrenda = nombrePrenda;
	}

	public Prenda()
	{
		this.detalleServicioPrenda =new ArrayList<DetalleServicioPrenda>();
	}
	
	public Long getIdPrenda() {
		return idPrenda;
	}

	public void setIdPrenda(Long idPrenda) {
		this.idPrenda = idPrenda;
	}

	public TipoPrenda getTipoPrenda() {
		return tipoPrenda;
	}

	public void setTipoPrenda(TipoPrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}

	public String getMarcaPrenda() {
		return marcaPrenda;
	}

	public void setMarcaPrenda(String marcaPrenda) {
		this.marcaPrenda = marcaPrenda;
	}

	public String getCaracteristicasPrenda() {
		return caracteristicasPrenda;
	}

	public void setCaracteristicasPrenda(String caracteristicasPrenda) {
		this.caracteristicasPrenda = caracteristicasPrenda;
	}
	
	public List<DetalleServicioPrenda> getDetalleServicioPrenda() {
		return detalleServicioPrenda;
	}

	public void setDetalleServicioPrenda(List<DetalleServicioPrenda> detalleServicioPrenda) {
		this.detalleServicioPrenda = detalleServicioPrenda;
	}
	
	public void addDetalle(DetalleServicioPrenda detalle)
	{
		this.detalleServicioPrenda.add(detalle);
	}
	
	public double getTotalPrenda()
	{
		double total=0.0;
		
		for (DetalleServicioPrenda detalleServicioPrenda2 : detalleServicioPrenda) {
			
			total += detalleServicioPrenda2.getServicio().getPrecioServicio();
		}
		return total;
	}
	
	
}
