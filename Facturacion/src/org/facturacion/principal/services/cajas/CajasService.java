package org.facturacion.principal.services.cajas;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.facturacion.principal.dao.DaoCajas;
import org.facturacion.principal.models.Caja;

public class CajasService implements ICajasService{
	
	private DaoCajas cajas;
	
	public CajasService() {
		cajas=new DaoCajas();// TODO Auto-generated constructor stub
	}

	@Override
	public void save(String nombre) throws Exception {
		try
		{
			Caja caja=new Caja();
			caja.setNombre(nombre);
			cajas.save(caja);
		}
		catch(SQLIntegrityConstraintViolationException ex)
		{
			throw new Exception("El nombre ingresado existe en la base de datos");
		}
		
	}

	@Override
	public List<Caja> findAll() throws Exception {
		return cajas.findAll();
	}

	@Override
	public Caja findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
