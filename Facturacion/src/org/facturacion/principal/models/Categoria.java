package org.facturacion.principal.models;

public class Categoria {
	
	private Long id;
	private String nombre;
	
	
	
	public Categoria() {
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}
	
	

}
