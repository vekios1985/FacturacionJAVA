package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.ItemFactura;
import org.facturacion.principal.models.Producto;
import org.facturacion.principal.utils.Conexion;

public class DaoItemFactura implements IDao<ItemFactura>{

	private DaoProducto daoProducto;
	private DaoFactura daoFactura;
	private Connection cnn;
	public DaoItemFactura(Connection con)
	{
		this.cnn=con;
		daoFactura=new DaoFactura(con);
		daoProducto=new DaoProducto(con);
	}
	
	@Override
	public List<ItemFactura> findAll() throws SQLException, Exception {
		List<ItemFactura>lista=new ArrayList<ItemFactura>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from items_factura"))
		{
			while(st.next())
			{
				lista.add(getItem(st));
			}
		}
		
		return lista;
	}
	
	public List<ItemFactura> findByFacturaId(Long id) throws SQLException, Exception {
		List<ItemFactura>lista=new ArrayList<ItemFactura>();
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from items_factura where id_factura=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			while(st.next())
			{
				lista.add(getItem(st));
			}
		}
		
		return lista;
	}

	private ItemFactura getItem(ResultSet st) throws SQLException, Exception {
		Producto producto=daoProducto.findById(st.getLong("id_producto"));
		Factura factura=daoFactura.findById(st.getLong("id_factura"));
		ItemFactura item=null;
		if(producto!=null&&factura!=null)
		{
			item=new ItemFactura(factura,producto,st.getDouble("cantidad"),st.getDouble("precio"));
			item.setId(st.getLong("id_item"));
		}
		
		return item;
	}

	@Override
	public ItemFactura findById(Long id) throws SQLException, Exception {
		ItemFactura p=null;
		try(
				PreparedStatement ps=cnn.prepareStatement("select * from items_factura where p.id_item=?"))
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
	public ItemFactura findByString(String name) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ItemFactura object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into items_factura (id_factura,id_producto,cantidad,precio) values(?,?,?,?)";
		}
		else
		{
			
				sql="update items_factura set id_factura=?,id_producto=?,cantidad=?,precio=? where id_item=?";
		}
		try(
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setLong(1, object.getFactura().getId());
						ps.setLong(2, object.getProducto().getId());
						ps.setDouble(3, object.getCantidad());
						ps.setDouble(4, object.getPrecio());
						if(object.getId()!=0)
							ps.setLong(5, object.getId());
						ps.executeUpdate();
						
					}
		
	}

	@Override
	public void delete(ItemFactura object) throws SQLException, Exception {
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement("delete from items_factura where id_item=?"))
		{
			ps.setLong(1, object.getId());			
			ps.executeUpdate();
			
		}
		
	}

}
