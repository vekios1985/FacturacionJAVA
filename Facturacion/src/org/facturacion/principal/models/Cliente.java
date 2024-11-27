package org.facturacion.principal.models;

public class Cliente {
	
	private Long id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private String localidad;
	private String email;
	private Integer dni;
	private Iva iva;
	
	
	
	public Cliente() {
		this.id=0L;
	}
	
	
	
	public Cliente(String nombre, String apellido, String telefono, String direccion, String localidad, String email,Integer dni,
			Iva iva) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.localidad = localidad;
		this.email = email;
		this.dni=dni;
		this.iva = iva;
		this.id=0L;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apelido) {
		this.apellido = apelido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Iva getIva() {
		return iva;
	}
	public void setIva(Iva iva) {
		this.iva = iva;
	}

}
