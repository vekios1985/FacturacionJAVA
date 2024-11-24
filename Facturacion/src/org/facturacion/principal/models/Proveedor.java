package org.facturacion.principal.models;

public class Proveedor {
	
	private Long id;
	private String nombre;
	private String telefono;
	private String email;
	private String direccion;
	
	
	
	
	public Proveedor() {
		
	}
	public Proveedor(String nombre, String telefono, String email, String direccion) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

}
