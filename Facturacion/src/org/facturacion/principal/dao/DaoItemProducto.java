package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.ItemProducto;
import org.facturacion.principal.models.Producto;
import org.facturacion.principal.models.Proveedor;


public class DaoItemProducto implements IDao<ItemProducto>{

	private DaoProducto daoProducto;
	private DaoProveedor daoProveedor;
	private Connection cnn;
	
	public DaoItemProducto(Connection con)
	{
		this.cnn=con;
		daoProducto=new DaoProducto(con);
		daoProveedor=new DaoProveedor(con);
	}
	@Override
	public List<ItemProducto> findAll() throws SQLException, Exception {
		List<ItemProducto>lista=new ArrayList<ItemProducto>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from items_productos"))
		{
			while(st.next())
			{
				lista.add(getItem(st));
			}
		}
		
		return lista;
	}

	private ItemProducto getItem(ResultSet st) throws SQLException, Exception {
		ItemProducto item=null;
		Producto producto=daoProducto.findById(st.getLong("id_producto"));
		Proveedor proveedor=daoProveedor.findById(st.getLong("id_proveedor"));
		if(producto!=null&&proveedor!=null)
		{
			item=new ItemProducto(producto,proveedor,st.getDouble("stock"),st.getDouble("precio"));
			item.setId(st.getLong("id_item"));
		}
		return item;
	}
	@Override
	public ItemProducto findById(Long id) throws SQLException, Exception {
		ItemProducto p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from items_productos where p.id_item=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getItem(st);
			}
		}
		
		return p;
	}

	@Override
	public ItemProducto findByString(String name) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ItemProducto object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into items_productos (id_producto,id_proveedor,stock,precio) values(?,?,?,?)";
		}
		else
		{
			
				sql="update items_productos set id_producto=?,id_proveedor=?,stock=?,precio=? where id_item=?";
		}
		try(
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setLong(1, object.getProducto().getId());
						ps.setLong(2, object.getProveedor().getId());
						ps.setDouble(3, object.getStock());
						ps.setDouble(4, object.getPrecio());
						if(object.getId()!=0)
							ps.setLong(5, object.getId());
						ps.executeUpdate();
						
					}
		
	}

	@Override
	public void delete(ItemProducto object) throws SQLException, Exception {
		try(
				PreparedStatement ps=cnn.prepareStatement("delete from items_productos where id_item=?"))
		{
			ps.setLong(1, object.getId());			
			ps.executeUpdate();
			
		}
		
	}

}
