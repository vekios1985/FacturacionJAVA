package org.facturacion.principal.models;

import java.util.Objects;

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
	
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true; // Es el mismo objeto
	        if (obj == null || getClass() != obj.getClass()) return false; // Verifica clase y null
	        Role role = (Role) obj;
	        return Objects.equals(nombre, role.getNombre()); // Compara usando Objects.equals
	    }

	    // Sobrescritura del m�todo hashCode
	    @Override
	    public int hashCode() {
	        return Objects.hash(nombre); // Genera hash basado en el id
	    }
}
