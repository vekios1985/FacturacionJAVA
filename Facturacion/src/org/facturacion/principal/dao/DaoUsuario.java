package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Role;
import org.facturacion.principal.models.Usuario;


public class DaoUsuario implements IDao<Usuario>{
	
	private Connection cnn;
	
	public DaoUsuario(Connection con) {
		this.cnn=con;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Usuario> findAll() throws SQLException, Exception {
		List<Usuario>lista=new ArrayList<Usuario>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from usuarios u inner join roles r on u.id_role=r.id_role"))
		{
			while(st.next())
			{
				lista.add(getUsuario(st));
			}
		}
		
		return lista;
	}

	private Usuario getUsuario(ResultSet st) throws SQLException {
		Usuario usuario=null;
		
		Role role=new Role();
		role.setId(st.getLong("r.id_role"));
		role.setNombre(st.getString("r.role"));
		usuario=new Usuario(role,st.getString("u.username"),st.getString("u.password"),
				st.getString("u.nombre"),st.getString("u.apellido"),st.getInt("u.dni"));
		usuario.setId(st.getLong("u.id_usuario"));
		
		return usuario;
	}

	@Override
	public Usuario findById(Long id) throws SQLException, Exception {
		Usuario p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from usuarios u inner join roles r on u.id_role=r.id_role where u.id_usuario=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getUsuario(st);
			}
		}
		
		return p;
	}

	@Override
	public Usuario findByString(String name) throws SQLException, Exception {
		Usuario p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from usuarios u inner join roles r on u.id_role=r.id_role where u.username=?"))
		{
			ps.setString(1, name);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getUsuario(st);
			}
		}
		
		return p;
	}

	@Override
	public void save(Usuario object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into usuarios (id_role,username,password,nombre,apellido,dni) values(?,?,?,?,?,?)";
		}
		else
		{
			
				sql="update usuarios set id_role=?,username=?,password=?,nombre=?,apellido=?,dni=? where id_usuario=?";
		}
		try(
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setLong(1, object.getRole().getId());
						ps.setString(2, object.getUsername());
						ps.setString(3, object.getPassword());
						ps.setString(4, object.getNombre());
						ps.setString(5, object.getApellido());
						ps.setInt(6, object.getDni());
						if(object.getId()!=0)
							ps.setLong(7, object.getId());
						ps.executeUpdate();
						
					}
		
	}

	@Override
	public void delete(Usuario object) throws SQLException, Exception {
		try(
				PreparedStatement ps=cnn.prepareStatement("delete from usuarios where id_usuario=?"))
		{
			ps.setLong(1, object.getId());			
			ps.executeUpdate();
			
		}
		
	}

}
