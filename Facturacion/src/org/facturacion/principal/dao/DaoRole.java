package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import org.facturacion.principal.models.Role;


public class DaoRole implements IDao<Role>{
	
	private Connection cnn;
	
	public DaoRole (Connection con)
	{
		this.cnn=con;
	}

	@Override
	public List<Role> findAll() throws SQLException, Exception {
		List<Role>lista=new ArrayList<Role>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from roles"))
		{
			while(st.next())
			{
				Role role=new Role();
				role.setNombre(st.getString("role"));
				role.setId(st.getLong("id_role"));
				lista.add(role);
			}
		}
		
		return lista;
	}

	@Override
	public Role findById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findByString(String name) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Role object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into roles role values(?)";
		}
		else
		{
			
				sql="update roles set role=? where id_role=?";
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
	public void delete(Role object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
