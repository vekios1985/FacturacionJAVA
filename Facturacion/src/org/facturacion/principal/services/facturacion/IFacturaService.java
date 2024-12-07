package org.facturacion.principal.services.facturacion;


import java.time.LocalDate;
import java.util.List;

import org.facturacion.principal.models.Caja;
import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.models.Venta;

public interface IFacturaService {
	
	public void saveFactura(Factura factura)throws Exception;
	
	public void saveVenta(Venta venta)throws Exception;
	
	public void saveTipoFactura(String name)throws Exception;
	
	public List<Factura>findAllFactura()throws Exception;
	public List<Factura>findFacturaFecha(LocalDate fecha)throws Exception;
	public List<Factura>findFacturaCliente(Integer dni)throws Exception;
	public List<Factura>findFacturaTipo(TipoFactura tipo)throws Exception;
	public Factura findFacturaNumero(String numero)throws Exception;
	public Venta findVenta(Factura factura)throws Exception;
	
	public List<Venta> findAllVenta()throws Exception;
	public List<Venta> findAllVenta(LocalDate inicio,LocalDate fin)throws Exception;
	public List<Venta> findAllVenta(LocalDate inicio,LocalDate fin,Caja caja)throws Exception;
	public List<Venta> findAllVenta(LocalDate inicio,LocalDate fin,Usuario user)throws Exception;
	
	public List<TipoFactura>findAllTipoFactura()throws Exception;
	
	public Integer getNumeracion(Long id_tipo_factura)throws Exception;
	
	public void saveFacturaYNumeracion(Factura factura,Venta venta) throws Exception;
	
	public Venta getVenta(Factura factura)throws Exception;

}
