package org.facturacion.principal.services.cajas;

import java.util.List;

import org.facturacion.principal.models.Caja;

public interface ICajasService {
	
	public void save(Caja caja)throws Exception;
	
	public List<Caja> findAll()throws Exception;
	
	public Caja findByName(String name)throws Exception;
}
