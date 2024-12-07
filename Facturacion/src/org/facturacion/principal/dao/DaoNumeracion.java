package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.facturacion.principal.models.Factura;



public class DaoNumeracion {
	

	private Connection cnn;

	
	public DaoNumeracion(Connection conn)
	{
		this.cnn=conn;
		
	}
	
	public Integer getNextNumeracion(Long id_tipo_factura)throws SQLException, Exception
	{
		Integer numero=null;
		try(			
				PreparedStatement ps=cnn.prepareStatement("select * from secuencia_facturas where id_tipo_factura=?"))
		{
			ps.setLong(1, id_tipo_factura);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				numero=rs.getInt("secuencia")+1;
			}
			else
				numero=0;
		}
		
		return numero;
	}
	
	
	
	public void saveNumeracion(Factura factura) throws SQLException, Exception {
		
		
			
			
			PreparedStatement ps=cnn.prepareStatement("update secuencia_facturas set secuencia=? where id_tipo_factura=?");
			ps.setInt(1, Integer.parseInt(factura.getNumero()));
			ps.setLong(2, factura.getTipoFactura().getId());
			ps.executeUpdate();
			
			
		
		
	}

}
