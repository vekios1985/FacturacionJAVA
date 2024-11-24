package org.facturacion.principal.models;

public class Iva {
	
	private Long id;
	private String nombre;
	
	
	
	public Iva() {
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

}
