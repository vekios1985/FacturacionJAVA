package org.facturacion.principal.models;

public class Producto {
	
	private Long id;
	private String nombre;
	private Categoria categoria;
	
	
	
	public Producto(String nombre, Categoria categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}
	public Producto() {
		this.id=0L;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

}
