package com.takmen.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Orden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrden;

	@Column(name = "fechaSolicitud")
	@Temporal(TemporalType.DATE)
	private Date fechaSolicitud;

	@NotNull
	@Column(name = "fechaEntrega")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	private Empleado empleado;

	@NotNull
	private int estadoOrden;

	@OneToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	@JoinColumn(name = "idOrden")
	private List<DetalleOP> detalleOP;

	public Orden() {
		this.detalleOP = new ArrayList<>();
	}

	@PrePersist
	public void prePersist()
	{
		fechaSolicitud=new Date();
	}
	public Long getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Long idOrden) {
		this.idOrden = idOrden;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public int getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(int estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public List<DetalleOP> getDetalleOP() {
		return detalleOP;
	}

	public void setDetalleOP(List<DetalleOP> detalleOP) {
		this.detalleOP = detalleOP;
	}
	
	public double granTotal()
	{
		double total=0;
		
		for (DetalleOP detalleOP2 : detalleOP) {
			
			total += detalleOP2.getCantidad()*detalleOP2.getPrenda().getTotalPrenda();
		}

		return total;
	}
	
	public void addDetalleOP(DetalleOP linea)
	{
		detalleOP.add(linea);
	}
}
