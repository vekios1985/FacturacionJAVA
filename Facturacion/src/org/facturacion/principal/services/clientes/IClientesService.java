package org.facturacion.principal.services.clientes;

import java.util.List;

import org.facturacion.principal.models.Cliente;
import org.facturacion.principal.models.Iva;

public interface IClientesService {
	
	public List<Cliente> findAll()throws Exception;
	public Cliente findById(Long id) throws Exception;
	public Cliente findByDni(Integer dni) throws Exception;
	public Cliente findByLastName(String name)throws Exception;
	public void save(Cliente cliente)throws Exception;
	public void delete(Cliente cliente)throws Exception;
	public List<Iva> listarIva()throws Exception;
	public void guardarIva(Iva iva)throws Exception;

}
