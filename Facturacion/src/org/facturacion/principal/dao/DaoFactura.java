package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Cliente;
import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.utils.Conexion;

public class DaoFactura implements IDao<Factura>{
	
	private DaoCliente daoCliente;
	
	
	public DaoFactura ()
	{
		daoCliente=new DaoCliente();
	}

	@Override
	public List<Factura> findAll() throws SQLException, Exception {
		List<Factura>lista=new ArrayList<Factura>();
		try(Connection cnn=Conexion.getConnection();
				ResultSet st=cnn.createStatement().executeQuery("select * from facturas f inner join tipos_facturas t on f.id_tipo_factura=t.id_tipo_factura"))
		{
			while(st.next())
			{
				lista.add(getFactura(st));
			}
		}
		
		return lista;
	}

	private Factura getFactura(ResultSet st) throws SQLException, Exception {
		Factura factura=null;
		Cliente cliente=daoCliente.findById(st.getLong("f.id_cliente"));
		if(cliente!=null)
		{
			factura=new Factura();
			TipoFactura tipo=new TipoFactura();
			tipo.setId(st.getLong("t.id_tipo_factura"));
			tipo.setTipo(st.getString("t.tipo_factura"));
			factura.setTipoFactura(tipo);
			factura.setNumero(st.getString("f.numero"));
			factura.setDescuent0(st.getDouble("f.descuento"));
			factura.setFecha(st.getDate("f.fecha").toLocalDate());
			factura.setCliente(cliente);
			factura.setId(st.getLong("f.id_factura"));
			
		}
		return factura;
	}

	@Override
	public Factura findById(Long id) throws SQLException, Exception {
		Factura p=null;
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement("select * from facturas f inner join tipos_facturas t on f.id_tipo_factura=t.id_tipo_factura where f.id_factura=?"))
		{
			ps.setLong(1, id);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getFactura(st);
			}
		}
		
		return p;
	}

	@Override
	public Factura findByString(String name) throws SQLException, Exception {
		Factura p=null;
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement("select * from facturas f inner join tipos_facturas t on f.id_tipo_factura=t.id_tipo_factura where f.numero=?"))
		{
			ps.setString(1, name);
			ResultSet st=ps.executeQuery();
			if(st.next())
			{
				p=getFactura(st);
			}
		}
		
		return p;
	}

	@Override
	public void save(Factura object) throws SQLException, Exception {
		String sql;
		if(object.getId()==0)
		{
			sql="insert into facturas numero,fecha,id_cliente,descuento,id_tipo_factura values(?,?,?,?,?)";
		}
		else
		{
			
				sql="update facturas set numero=?,fecha=?,id_cliente=?,descuento=?,id_tipo_factura=? where id_factura=?";
		}
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setString(1, object.getNumero());
						ps.setDate(2, Date.valueOf(object.getFecha()));
						ps.setLong(3, object.getCliente().getId());
						ps.setDouble(4, object.getDescuent0());
						ps.setLong(5, object.getTipoFactura().getId());
						if(object.getId()!=0)
							ps.setLong(6, object.getId());
						ps.executeUpdate();
						
					}
	}

	@Override
	public void delete(Factura object) throws SQLException, Exception {
		try(Connection cnn=Conexion.getConnection();
				PreparedStatement ps=cnn.prepareStatement("delete from facturas where id_factura=?"))
		{
			ps.setLong(1, object.getId());			
			ps.executeUpdate();
			
		}
		
	}

}
