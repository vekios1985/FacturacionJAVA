package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Caja;


public class DaoCajas implements IDao<Caja>{
	
	private Connection cnn;
	
	public DaoCajas(Connection con) {
		this.cnn=con;
	}

	@Override
	public List<Caja> findAll() throws SQLException, Exception {
		List<Caja>lista=new ArrayList<Caja>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from cajas"))
		{
			while(st.next())
			{
				Caja role=new Caja();
				role.setNombre(st.getString("nombre"));
				role.setId(st.getLong("id_caja"));
				lista.add(role);
			}
		}
		
		return lista;
	}

	@Override
	public Caja findById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caja findByString(String name) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Caja object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into cajas (nombre) values(?)";
		}
		else
		{
			
				sql="update cajas set nombre=? where id_caja=?";
		}
		try(
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setString(1, object.getNombre());
						if(object.getId()!=0)
							ps.setLong(2, object.getId());
						ps.executeUpdate();
						
					}
		
	}

	@Override
	public void delete(Caja object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	

}
