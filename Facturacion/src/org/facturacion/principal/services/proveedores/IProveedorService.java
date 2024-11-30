package org.facturacion.principal.services.proveedores;

import java.util.List;

import org.facturacion.principal.models.Proveedor;

public interface IProveedorService {
	
	
	public List<Proveedor> findAll()throws Exception;
	
	public Proveedor findByID(Long id)throws Exception;
	
	public Proveedor findByCuit(Long cuit)throws Exception;
	
	public Proveedor findByName(String name)throws Exception;
	
	public void save(Proveedor proveedor)throws Exception;
	
	public List<Proveedor> ListByCuit(String cuit)throws Exception;
	public List<Proveedor> ListByName(String name)throws Exception;

}
