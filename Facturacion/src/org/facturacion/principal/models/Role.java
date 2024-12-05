package org.facturacion.principal.models;

public class Role {
	
	private Long id;
	private String nombre;
	
	
	
	public Role() {
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
	public String toString()
	{
		return nombre;
	}
}
