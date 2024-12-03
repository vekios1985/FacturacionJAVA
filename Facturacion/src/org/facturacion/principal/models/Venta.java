package org.facturacion.principal.models;

import java.util.ArrayList;
import java.util.List;

public class Venta {
	
	private Long id;
	private Factura factura;
	private Caja caja;
	private Usuario usuario;
	private List<ItemFactura> items;
	
	
	
	
	public Venta() {
		this.items=new ArrayList<ItemFactura>();
		id=0L;
	}
	
	public void addItem(ItemFactura item)
	{
		this.items.add(item);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Caja getCaja() {
		return caja;
	}
	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItemFactura> getItems() {
		return items;
	}
	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
	
	public Double getTotal()
	{
		Double total=0D;
		for(ItemFactura i:items)
		{
			total+=i.getMonto();
		}
		total=total-total*this.factura.getDescuento()/100;
		return total;
	}
	
	public Double getSubTotal()
	{
		Double total=0D;
		for(ItemFactura i:items)
		{
			total+=i.getMonto();
		}
		
		return total;
	}

}
