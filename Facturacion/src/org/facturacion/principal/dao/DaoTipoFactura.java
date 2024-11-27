package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.utils.Conexion;

public class DaoTipoFactura implements IDao<TipoFactura>{

	@Override
	public List<TipoFactura> findAll() throws SQLException, Exception {
		List<TipoFactura>lista=new ArrayList<TipoFactura>();
		try(Connection cnn=Conexion.getConnection();
				ResultSet st=cnn.createStatement().executeQuery("select * from tipo_facturas"))
		{
			while(st.next())
			{
				
				TipoFactura tipo=new TipoFactura();
				tipo.setId(st.getLong("id_tipo_factura"));
				tipo.setTipo(st.getString("tipo_factura"));
				lista.add(tipo);
			}
		}
		
		return lista;
	}

	@Override
	public TipoFactura findById(Long id) throws SQLException, Exception {
		TipoFactura p=null;
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement("select * from tipo_facturas where p.id_tipo_factura=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=new TipoFactura();
				p.setId(st.getLong("id_tipo_factura"));
				p.setTipo(st.getString("tipo_factura"));
			}
		}
		
		return p;
	}

	@Override
	public TipoFactura findByString(String name) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(TipoFactura object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		String sql;
		if(object.getId()==0)
		{
			sql="insert into tipo_facturas tipo_factura values(?)";
		}
		else
		{
			
				sql="update tipo_facturas set tipo_factura=? where id_tipo_factura=?";
		}
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setString(1, object.getTipo());
						if(object.getId()!=0)
							ps.setLong(2, object.getId());
						ps.executeUpdate();
						
					}
	}

	@Override
	public void delete(TipoFactura object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
