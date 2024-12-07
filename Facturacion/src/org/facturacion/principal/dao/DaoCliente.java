package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Cliente;
import org.facturacion.principal.models.Iva;


public class DaoCliente implements IDao<Cliente>{
	
private Connection cnn;
	
	public DaoCliente(Connection con) {
		this.cnn=con;
	}

	@Override
	public List<Cliente> findAll() throws SQLException, Exception {
		List<Cliente>lista=new ArrayList<Cliente>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from clientes c inner join iva i on c.id_iva=i.id_iva"))
		{
			while(st.next())
			{
				lista.add(getCliente(st));
			}
		}
		
		return lista;
	}

	private Cliente getCliente(ResultSet st) throws SQLException {
		Cliente cliente;
		Iva iva=new Iva();
		iva.setId(st.getLong("i.id_iva"));
		iva.setNombre(st.getString("i.tipo_iva"));
		cliente=new Cliente(st.getString("c.nombre"),st.getString("c.apellido"),st.getString("c.telefono"),
				st.getString("c.direccion"),st.getString("c.localidad"),st.getString("c.email"),st.getInt("c.dni"),iva);
		cliente.setId(st.getLong("c.id_cliente"));
		return cliente;
	}

	@Override
	public Cliente findById(Long id) throws SQLException, Exception {
		Cliente p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from clientes c inner join iva i on c.id_iva=i.id_iva where c.id_cliente=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getCliente(st);
			}
		}
		
		return p;
	}

	@Override
	public Cliente findByString(String name) throws SQLException, Exception {
		Cliente p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from clientes c inner join iva i on c.id_iva=i.id_iva where c.apellido=?"))
		{
			ps.setString(1, name);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getCliente(st);
			}
		}
		
		return p;
	}

	@Override
	public void save(Cliente object) throws SQLException, Exception {
		String sql;
		
		if(object.getId()==0)
		{
			sql="insert into clientes (nombre,apellido,telefono,direccion,localidad,email,dni,id_iva) values(?,?,?,?,?,?,?,?)";
		}
		else
		{
			
				sql="update clientes set nombre=?,apellido=?,telefono=?,direccion=?,localidad=?,email=?,dni=?,id_iva=? where id_cliente=?";
		}
		
		try(
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setString(1, object.getNombre());
						ps.setString(2, object.getApellido());
						ps.setString(3, object.getTelefono());
						ps.setString(4, object.getDireccion());
						ps.setString(5, object.getLocalidad());
						ps.setString(6, object.getEmail());
						ps.setInt(7, object.getDni());
						ps.setLong(8, object.getIva().getId());
						if(object.getId()!=0)
							ps.setLong(9, object.getId());
						ps.executeUpdate();
						
					}
	}

	@Override
	public void delete(Cliente object) throws SQLException, Exception {
		try(
				PreparedStatement ps=cnn.prepareStatement("delete from clientes where id_cliente=?"))
		{
			ps.setLong(1, object.getId());			
			ps.executeUpdate();
			
		}
	}

}
