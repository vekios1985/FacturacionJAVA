package org.facturacion.principal.models;

import java.util.Objects;

public class Iva {
	
	   @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true; // Es el mismo objeto
	        if (obj == null || getClass() != obj.getClass()) return false; // Verifica clase y null
	        Iva iva = (Iva) obj;
	        return Objects.equals(id, iva.id); // Compara usando Objects.equals
	    }

	    // Sobrescritura del método hashCode
	    @Override
	    public int hashCode() {
	        return Objects.hash(id); // Genera hash basado en el id
	    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre;
	}
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
