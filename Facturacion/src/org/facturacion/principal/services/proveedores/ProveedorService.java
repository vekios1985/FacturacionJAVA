package org.facturacion.principal.services.proveedores;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.facturacion.principal.dao.DaoProveedor;
import org.facturacion.principal.models.Proveedor;
import org.facturacion.principal.utils.Conexion;

public class ProveedorService implements IProveedorService{
	
	private DaoProveedor daoProveedor;
	
	public ProveedorService()throws Exception
	{
		daoProveedor=new DaoProveedor(Conexion.getConnection());
	}

	@Override
	public List<Proveedor> findAll() throws Exception {
		return daoProveedor.findAll();
	}

	@Override
	public Proveedor findByID(Long id) throws Exception {
		return daoProveedor.findById(id);
	}

	@Override
	public Proveedor findByCuit(Long cuit) throws Exception {
		
		Optional<Proveedor> p=findAll().stream().filter(x->x.getCuit().equals(cuit)).findFirst();
		
		if(p.isPresent())
			return p.get();
		else
			return null;
	}

	@Override
	public Proveedor findByName(String name) throws Exception {
		return daoProveedor.findByString(name);
	}

	@Override
	public void save(Proveedor proveedor) throws Exception {
		try
		
		{daoProveedor.save(proveedor);
		}
		catch(SQLIntegrityConstraintViolationException exeption)
		{
			throw new Exception("El CUIT ingresado existe en la base de datos");
		
	}
	}

	@Override
	public List<Proveedor> ListByCuit(String cuit) throws Exception {
		List<Proveedor> lista=new ArrayList<Proveedor>();
		for(Proveedor p:findAll())
		{
			if(p.getCuit().toString().contains(cuit))
				lista.add(p);
		}
		return lista;
	}

	@Override
	public List<Proveedor> ListByName(String name) throws Exception {
		List<Proveedor> lista=new ArrayList<Proveedor>();
		for(Proveedor p:findAll())
		{
			if(p.getNombre().toString().toLowerCase().contains(name))
				lista.add(p);
		}
		return lista;
	}

}
