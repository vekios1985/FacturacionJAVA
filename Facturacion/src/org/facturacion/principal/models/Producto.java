package org.facturacion.principal.models;

public class Producto {
	
	private Long id;
	private String nombre;
	private Categoria categoria;
	private Long codigo;
	
	
	
	public Producto(String nombre, Categoria categoria,Long codigo) {
		this.id=0L;
		this.nombre = nombre;
		this.categoria = categoria;
		this.codigo=codigo;
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
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
