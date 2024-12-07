package org.facturacion.principal.models;

public class Usuario {
	
	private Long id;
	private Role role;
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private Integer dni;
	
	
	
	
	public Usuario() {
		this.id=0L;
	}
	public Usuario(Role role, String username, String password, String nombre, String apellido, Integer dni) {
		super();
		this.role = role;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.id=0L;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public String toString()
	{
		return username;
	}

}
