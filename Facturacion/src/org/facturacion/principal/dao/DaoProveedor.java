package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Proveedor;


public class DaoProveedor implements IDao<Proveedor>{
	
	private Connection cnn;
	
	public DaoProveedor(Connection con) {
		// TODO Auto-generated constructor stub
		this.cnn=con;
	}

	@Override
	public List<Proveedor> findAll() throws SQLException, Exception {
		List<Proveedor>lista=new ArrayList<Proveedor>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from proveedores"))
		{
			while(st.next())
			{
				lista.add(getProveedor(st));
			}
		}
		
		return lista;
	}

	private Proveedor getProveedor(ResultSet st)throws SQLException, Exception {
		Proveedor p=new Proveedor(st.getString("nombre"),st.getString("telefono"),st.getString("email"),st.getString("direccion"),st.getLong("cuit"));
		p.setId(st.getLong("id_proveedor"));
		return p;
	}

	@Override
	public Proveedor findById(Long id) throws SQLException, Exception {
		Proveedor p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from proveedores where id_proveedor=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getProveedor(st);
			}
		}
		
		return p;
	}

	@Override
	public Proveedor findByString(String name) throws SQLException, Exception {
		Proveedor p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from proveedores where p.nombre=?"))
		{
			ps.setString(1, name);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getProveedor(st);
			}
		}
		
		return p;
	}

	@Override
	public void save(Proveedor object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into proveedores (nombre,telefono,email,direccion,cuit) values(?,?,?,?,?)";
		}
		else
		{
			
				sql="update proveedores set nombre=?,telefono=?,email=?,direccion=?,cuit=? where id_proveedor=?";
		}
		try(
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setString(1, object.getNombre());
						ps.setString(2, object.getTelefono());
						ps.setString(3, object.getEmail());
						ps.setString(4, object.getDireccion());
						ps.setLong(5, object.getCuit());
						if(object.getId()!=0)
							ps.setLong(6, object.getId());
						ps.executeUpdate();
						
					}
	}

	@Override
	public void delete(Proveedor object) throws SQLException, Exception {
		try(
				PreparedStatement ps=cnn.prepareStatement("delete from proveedores where id_proveedor=?"))
		{
			ps.setLong(1, object.getId());			
			ps.executeUpdate();
			
		}
		
	}

}
