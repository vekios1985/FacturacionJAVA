package org.facturacion.principal.models;

public class ItemFactura {
	
	private Long id;
	private Factura factura;
	private Producto producto;
	private Double cantidad;
	private Double precio;
	
	
	
	
	public ItemFactura() {
		this.id=0L;
	}
	public ItemFactura(Factura factura, Producto producto, Double cantidad, Double precio) {
		super();
		this.factura = factura;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Double getMonto()
	{
		return this.cantidad*this.precio;
	}

}
