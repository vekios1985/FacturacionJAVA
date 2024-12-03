package org.facturacion.principal.services.facturacion;

import java.util.List;

import org.facturacion.principal.dao.DaoFactura;
import org.facturacion.principal.dao.DaoNumeracion;
import org.facturacion.principal.dao.DaoTipoFactura;
import org.facturacion.principal.dao.DaoVentas;
import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.models.Venta;

public class FacturaService implements IFacturaService

{
	private DaoFactura facturas;
	private DaoVentas ventas;
	private DaoTipoFactura tipos;
	private DaoNumeracion numeracion;
	
	public FacturaService() {
		facturas=new DaoFactura();
		ventas=new DaoVentas();
		tipos=new DaoTipoFactura();
		numeracion=new DaoNumeracion();
	}

	@Override
	public void saveFactura(Factura factura) throws Exception {
		
			facturas.save(factura);
		
		
	}

	@Override
	public void saveVenta(Venta venta) throws Exception {
		ventas.save(venta);
		
	}

	@Override
	public List<Factura> findAllFactura() throws Exception {
		return facturas.findAll();
	}

	@Override
	public List<Venta> findAllVenta() throws Exception {
		return ventas.findAll();
	}

	@Override
	public List<TipoFactura> findAllTipoFactura() throws Exception {

		return tipos.findAll();
	}

	@Override
	public Integer getNumeracion(Long id_tipo_factura) throws Exception {
		return numeracion.getNextNumeracion(id_tipo_factura);
	}

	@Override
	public void saveFacturaYNumeracion(Factura factura) throws Exception {
		numeracion.saveFacturaYNumeracion(factura);
		
	}

	@Override
	public void saveTipoFactura(String name) throws Exception {
		// TODO Auto-generated method stub
		TipoFactura n_tipo=new TipoFactura();
		n_tipo.setTipo(name);
		tipos.save(n_tipo);
	}

}
