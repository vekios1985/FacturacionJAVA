package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.facturacion.principal.models.Factura;
import org.facturacion.principal.utils.Conexion;

public class DaoNumeracion {
	
	private DaoFactura daoFactura;
	
	public DaoNumeracion()
	{
		daoFactura=new DaoFactura();
	}
	
	public Integer getNextNumeracion(Long id_tipo_factura)throws SQLException, Exception
	{
		Integer numero=null;
		try(Connection cnn=Conexion.getConnection();				
				PreparedStatement ps=cnn.prepareStatement("select * from secuencia_facturas where id_tipo_factura=?"))
		{
			ps.setLong(1, id_tipo_factura);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				numero=rs.getInt("secuencia")+1;
			}
		}
		
		return numero;
	}
	
	public void saveFacturaYNumeracion(Factura factura) throws SQLException, Exception {
		Connection cnn=Conexion.getConnection();
		try
		{
			cnn.setAutoCommit(false);
			daoFactura.save(factura);
			PreparedStatement ps=cnn.prepareStatement("update secuencia_facturas set secuencia=? where id_tipo_factura=?");
			ps.setInt(1, Integer.parseInt(factura.getNumero()));
			ps.setLong(2, factura.getTipoFactura().getId());
			ps.executeUpdate();
			cnn.commit();
			
		}
		catch(SQLException |NumberFormatException ex)
		{
			cnn.rollback();
			throw ex;
		}
		finally
		{
			cnn.setAutoCommit(true);
		}
	}

}
