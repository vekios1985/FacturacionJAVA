package org.facturacion.principal.services.facturacion;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.facturacion.principal.dao.DaoFactura;
import org.facturacion.principal.dao.DaoItemFactura;
import org.facturacion.principal.dao.DaoItemProducto;
import org.facturacion.principal.dao.DaoNumeracion;
import org.facturacion.principal.dao.DaoTipoFactura;
import org.facturacion.principal.dao.DaoVentas;
import org.facturacion.principal.models.Caja;
import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.ItemFactura;
import org.facturacion.principal.models.ItemProducto;
import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.models.Venta;
import org.facturacion.principal.utils.Conexion;

public class FacturaService implements IFacturaService

{
	private DaoFactura facturas;
	private DaoVentas ventas;
	private DaoTipoFactura tipos;
	private DaoNumeracion numeracion;
	private Connection cnn;
	private DaoItemFactura daoItemFactura;
	private DaoItemProducto daoItemProducto;
	
	public FacturaService()throws Exception {
		cnn=Conexion.getConnection();
		facturas=new DaoFactura(cnn);
		ventas=new DaoVentas(cnn);
		tipos=new DaoTipoFactura(cnn);
		numeracion=new DaoNumeracion(cnn);
		daoItemFactura=new DaoItemFactura(cnn);
		daoItemProducto=new DaoItemProducto(cnn);
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
	public void saveFacturaYNumeracion(Factura factura,Venta venta) throws Exception {
		
		try
		{
			cnn.setAutoCommit(false);
			numeracion.saveNumeracion(factura);
			facturas.save(factura);
			
			Long idFactura=facturas.findByString(factura.getNumero().toString()).getId();
			//falta obtener el id de la factura generada
			factura.setId(idFactura);
			venta.setFactura(factura);
			ventas.save(venta);
			List<ItemProducto>listaProductos=daoItemProducto.findAll();
			for(ItemFactura item:venta.getItems())
			{
				item.getFactura().setId(idFactura);
				daoItemFactura.save(item);
				ItemProducto itemP=listaProductos.stream().filter(p->
				p.getProducto().getCodigo().equals(item.getProducto().getCodigo())).findFirst().orElse(null);
				if(itemP!=null)
				{
					itemP.setStock(itemP.getStock()-item.getCantidad());
					daoItemProducto.save(itemP);
				}
			}
			
			cnn.commit();
		}
		catch(Exception ex)
		{
			cnn.rollback();
			throw ex;
		}
		finally
		{
			cnn.setAutoCommit(true);
		}
		
		
	}

	@Override
	public void saveTipoFactura(String name) throws Exception {
		// TODO Auto-generated method stub
		TipoFactura n_tipo=new TipoFactura();
		n_tipo.setTipo(name);
		tipos.save(n_tipo);
	}

	@Override
	public Venta getVenta(Factura factura) throws Exception {
		for(Venta venta:findAllVenta())
		{
			if(venta.getFactura().getId().equals(factura.getId()))
			{
				return venta;
			}
		}
		return null;
	}

	@Override
	public List<Factura> findFacturaFecha(LocalDate fecha) throws Exception {
		
		List<Factura>lista=new ArrayList<Factura>();
		for(Factura factura:findAllFactura())
		{
			if(factura.getFecha().equals(fecha))
			{
				lista.add(factura);
			}
		}
		return lista;
	}

	@Override
	public List<Factura> findFacturaCliente(Integer dni) throws Exception {
		List<Factura>lista=new ArrayList<Factura>();
		for(Factura factura:findAllFactura())
		{
			if(factura.getCliente().getDni().equals(dni))
			{
				lista.add(factura);
			}
		}
		return lista;
	}

	@Override
	public List<Factura> findFacturaTipo(TipoFactura tipo) throws Exception {
		List<Factura>lista=new ArrayList<Factura>();
		for(Factura factura:findAllFactura())
		{
			if(factura.getTipoFactura().getId().equals(tipo.getId()))
			{
				lista.add(factura);
			}
		}
		return lista;
	}

	@Override
	public Factura findFacturaNumero(String numero) throws Exception {
		Factura factura=null;
		
		factura=findAllFactura().stream().filter(f->f.getNumero().equals(numero)).findFirst().orElse(null);
		
		return factura;
	}

	@Override
	public Venta findVenta(Factura factura) throws Exception {
		Venta venta=null;
		
		venta=findAllVenta().stream().filter(v->v.getFactura().getId().equals(factura.getId())).findFirst().orElse(null);
		
		return venta;
	}

	@Override
	public List<Venta> findAllVenta(LocalDate inicio, LocalDate fin) throws Exception {
		
		
	    return findAllVenta().stream()
	        .filter(v -> {
	            LocalDate fechaVenta = v.getFactura().getFecha();
	            
	            
	            return (fechaVenta.isEqual(inicio) || fechaVenta.isEqual(fin) 
	                    || (fechaVenta.isAfter(inicio) && fechaVenta.isBefore(fin)));
	        })
	        .collect(Collectors.toList());
	}

	@Override
	public List<Venta> findAllVenta(LocalDate inicio, LocalDate fin, Caja caja) throws Exception {
		return findAllVenta().stream()
		        .filter(v -> {
		            LocalDate fechaVenta = v.getFactura().getFecha();
		            return ((fechaVenta.isAfter(inicio) && fechaVenta.isBefore(fin)) 
		                    || fechaVenta.isEqual(inicio) 
		                    || fechaVenta.isEqual(fin))&&v.getCaja().getId().equals(caja.getId());
		        })
		        .collect(Collectors.toList());
	}

	@Override
	public List<Venta> findAllVenta(LocalDate inicio, LocalDate fin, Usuario user) throws Exception {
		return findAllVenta().stream()
		        .filter(v -> {
		            LocalDate fechaVenta = v.getFactura().getFecha();
		            return ((fechaVenta.isAfter(inicio) && fechaVenta.isBefore(fin)) 
		                    || fechaVenta.isEqual(inicio) 
		                    || fechaVenta.isEqual(fin))&&v.getUsuario().getId().equals(user.getId());
		        })
		        .collect(Collectors.toList());
	}

}
