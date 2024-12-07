package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Iva;
import org.facturacion.principal.utils.Conexion;

public class DaoIva implements IDao<Iva>{
	
	private Connection cnn;
	
	public DaoIva(Connection con) {
		// TODO Auto-generated constructor stub
		this.cnn=con;
	}

	@Override
	public List<Iva> findAll() throws SQLException, Exception {
		List<Iva>lista=new ArrayList<Iva>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from iva"))
		{
			while(st.next())
			{
				Iva iva=new Iva();
				iva.setId(st.getLong("id_iva"));
				iva.setNombre(st.getString("tipo_iva"));
				lista.add(iva);
			}
		}
		
		return lista;
	}

	@Override
	public Iva findById(Long id) throws SQLException, Exception {
		Iva p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from iva where p.id_iva=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=new Iva();
				p.setId(st.getLong("id_iva"));
				p.setNombre(st.getString("tipo_iva"));
			}
		}
		
		return p;
	}

	@Override
	public Iva findByString(String name) throws SQLException, Exception {
		Iva p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from iva where p.tipo_iva=?"))
		{
			ps.setString(1, name);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=new Iva();
				p.setId(st.getLong("id_iva"));
				p.setNombre(st.getString("tipo_iva"));
			}
		}
		
		return p;
	}

	@Override
	public void save(Iva object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into iva (tipo_iva) values(?)";
		}
		else
		{
			
				sql="update iva set tipo_iva=? where id_iva=?";
		}
		System.out.println("g");
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
	public void delete(Iva object) throws SQLException, Exception {
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement("delete from iva where id_iva=?"))
		{
			ps.setLong(1, object.getId());			
			ps.executeUpdate();
			
		}
	}

}
