package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.utils.Conexion;

public class DaoCategoria implements IDao<Categoria>{

	@Override
	public List<Categoria> findAll() throws SQLException, Exception {
		List<Categoria>lista=new ArrayList<Categoria>();
		try(Connection cnn=Conexion.getConnection();
				ResultSet st=cnn.createStatement().executeQuery("select * from categorias_productos"))
		{
			while(st.next())
			{
				lista.add(getCategoria(st));
			}
		}
		
		return lista;
	}

	private Categoria getCategoria(ResultSet st) throws SQLException {
		Categoria cat=new Categoria();
		cat.setId(st.getLong("id_categoria"));
		cat.setNombre(st.getString("nombre"));
		return cat;
	}

	@Override
	public Categoria findById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria findByString(String name) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Categoria object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into categorias_productos nombre values(?)";
		}
		else
		{
			
				sql="update categorias_productos set nombre=? where id_categoria=?";
		}
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setString(1, object.getNombre());
						if(object.getId()!=0)
							ps.setLong(2, object.getId());
						ps.executeUpdate();
						
					}
	}

	@Override
	public void delete(Categoria object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
