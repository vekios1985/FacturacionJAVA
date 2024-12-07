package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.models.Producto;


public class DaoProducto implements IDao<Producto>{
	
	private Connection cnn;
	
	public DaoProducto(Connection con) {
		// TODO Auto-generated constructor stub
		this.cnn=con;
	}

	@Override
	public List<Producto> findAll() throws SQLException, Exception {
		List<Producto>lista=new ArrayList<Producto>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from productos p inner join categorias_productos c on p.id_categoria=c.id_categorias"))
		{
			while(st.next())
			{
				lista.add(getProducto(st));
			}
		}
		
		return lista;
	}

	private Producto getProducto(ResultSet st) throws SQLException {
		Categoria cat=new Categoria();
		cat.setId(st.getLong("c.id_categorias"));
		cat.setNombre(st.getString("c.nombre"));
		Producto p=new Producto(st.getString("p.nombre"), cat,st.getLong("codigo"));
		p.setId(st.getLong("p.id_producto"));
		return p;
	}

	@Override
	public Producto findById(Long id) throws SQLException, Exception{
		Producto p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from productos p inner join categorias_productos c on p.id_categoria=c.id_categorias where p.id_producto=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getProducto(st);
			}
		}
		
		return p;
	}
	
	
	public Producto findByCodigo(Long codigo) throws SQLException, Exception{
		Producto p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from productos p inner join categorias_productos c on p.id_categoria=c.id_categoria where p.codigo=?"))
		{
			ps.setLong(1, codigo);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getProducto(st);
			}
		}
		
		return p;
	}

	@Override
	public Producto findByString(String name) throws SQLException, Exception{
		Producto p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from productos p inner join categorias_productos c on p.id_categoria=c.id_categoria where p.nombre=?"))
		{
			ps.setString(1, name);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getProducto(st);
			}
		}
		
		return p;
	}

	@Override
	public void save(Producto object) throws SQLException, Exception{
		
		
		String sql;
		if(object.getId()==0)
		{
			sql="insert into productos (nombre,id_categoria,codigo) values(?,?,?)";
		}
		else
		{
			
				sql="update productos set nombre=?,id_categoria=?,codigo=? where id_producto=?";
		}
		try(
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
			
						ps.setString(1, object.getNombre());
						ps.setLong(2, object.getCategoria().getId());
						ps.setLong(3, object.getCodigo());
						if(object.getId()!=0)
							ps.setLong(4, object.getId());
						ps.executeUpdate();
						
					}
		
	}

	@Override
	public void delete(Producto object) throws SQLException, Exception{
		// TODO Auto-generated method stub
		try(
				PreparedStatement ps=cnn.prepareStatement("delete from productos where id_producto=?"))
		{
			ps.setLong(1, object.getId());			
			ps.executeUpdate();
			
		}
	}

}
