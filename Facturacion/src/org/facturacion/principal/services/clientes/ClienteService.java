package org.facturacion.principal.services.clientes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.facturacion.principal.dao.DaoCliente;
import org.facturacion.principal.dao.DaoIva;
import org.facturacion.principal.models.Cliente;
import org.facturacion.principal.models.Iva;
import org.facturacion.principal.utils.Conexion;

public class ClienteService implements IClientesService{

	private DaoCliente clientes;
	private DaoIva iva;
	private Connection cnn;
	public ClienteService()throws Exception
	{
		cnn=Conexion.getConnection();
		clientes=new DaoCliente(cnn);
		iva=new DaoIva(cnn);
	}
	
	@Override
	public List<Cliente> findAll() throws Exception {
		return clientes.findAll();
	}

	@Override
	public Cliente findById(Long id) throws Exception {
		return clientes.findById(id);
	}

	@Override
	public Cliente findByLastName(String name) throws Exception {
		return clientes.findByString(name);
	}

	@Override
	public void save(Cliente cliente) throws Exception {
		try
		{
			clientes.save(cliente);
		}
		catch(SQLIntegrityConstraintViolationException ex)
		{
			throw new Exception("El dni ingresado ya está registrado");
		}
		
		
	}

	@Override
	public void delete(Cliente cliente) throws Exception {
		clientes.delete(cliente);
		
	}
	
	@Override
	public List<Iva> listarIva() throws SQLException, Exception
	{
		return iva.findAll();
	}

	@Override
	public void guardarIva(Iva n_iva) throws Exception {
		List<Iva>lista=listarIva();
		
		for(Iva i:lista)
		{
			if(i.getNombre().equals(n_iva.getNombre()))
				throw new Exception("Iva ya registrado");
		}
		System.out.println(n_iva.getNombre());
		this.iva.save(n_iva);
		
	}

	@Override
	public Cliente findByDni(Integer dni) throws Exception {
		
		Optional<Cliente>cliente=findAll().stream().filter(p->p.getDni().equals(dni)).findFirst();
		
		if(cliente.isPresent())
			return cliente.get();
		else
			return null;
	}

}
