package org.facturacion.principal.models;

import java.time.LocalDate;

public class Factura {
	
	private Long id;
	private String numero;
	private LocalDate fecha;
	private Cliente cliente;
	private Double descuento;
	private TipoFactura tipoFactura;
	private String observacion;
	
	
	
	
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Factura() {
		this.descuento=0D;
		this.id=0L;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public TipoFactura getTipoFactura() {
		return tipoFactura;
	}
	public void setTipoFactura(TipoFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

}
