package org.facturacion.principal.models;

public class TipoFactura {
	
	private Long id;
	private String tipo;
	
	
	
	public TipoFactura() {
		this.id=0L;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString()
	{
		return tipo;
	}
	

}
