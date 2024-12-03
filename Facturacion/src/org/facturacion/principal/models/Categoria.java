package org.facturacion.principal.models;

import java.util.Objects;

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
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Es el mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false; // Verifica clase y null
        Categoria categoria = (Categoria) obj;
        return Objects.equals(id, categoria.getId()); // Compara usando Objects.equals
    }

    // Sobrescritura del método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id); // Genera hash basado en el id
    }
	

}
