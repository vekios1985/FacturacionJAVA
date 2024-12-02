package org.facturacion.principal.models;

public class ItemProducto {
	
	private Long id;
	private Producto producto;
	private Proveedor proveedor;
	private Double stock;
	private Double precio;
	
	
	
	
	public ItemProducto() {
		this.id=0L;
		this.stock=0D;
		this.precio=0D;
	}
	public ItemProducto(Producto producto, Proveedor proveedor, Double stock, Double precio) {
		
		this.producto = producto;
		this.proveedor = proveedor;
		this.stock = stock;
		this.precio = precio;
		this.id=0L;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
		this.stock = stock;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	

}
