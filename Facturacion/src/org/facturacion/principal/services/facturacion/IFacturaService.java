package org.facturacion.principal.services.facturacion;

import java.sql.SQLException;
import java.util.List;

import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.models.Venta;

public interface IFacturaService {
	
	public void saveFactura(Factura factura)throws Exception;
	
	public void saveVenta(Venta venta)throws Exception;
	
	public void saveTipoFactura(String name)throws Exception;
	
	public List<Factura>findAllFactura()throws Exception;
	
	public List<Venta> findAllVenta()throws Exception;
	
	public List<TipoFactura>findAllTipoFactura()throws Exception;
	
	public Integer getNumeracion(Long id_tipo_factura)throws Exception;
	
	public void saveFacturaYNumeracion(Factura factura) throws Exception;

}
