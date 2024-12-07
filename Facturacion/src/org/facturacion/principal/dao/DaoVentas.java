package org.facturacion.principal.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Caja;
import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.models.Venta;


public class DaoVentas implements IDao<Venta>{
	
	private DaoUsuario daoUsuario;
	private DaoFactura daoFactura;
	private DaoItemFactura daoItem;
	private Connection cnn;
	public DaoVentas(Connection con)
	{
		this.cnn=con;
		daoFactura=new DaoFactura(cnn);
		daoUsuario=new DaoUsuario(cnn);
		daoItem=new DaoItemFactura(cnn);
	}

	@Override
	public List<Venta> findAll() throws SQLException, Exception {
		List<Venta>lista=new ArrayList<Venta>();
		try(
				ResultSet st=cnn.createStatement().executeQuery("select * from ventas v inner join cajas c on v.id_caja=c.id_caja"))
		{
			while(st.next())
			{
				lista.add(getVenta(st));
			}
		}
		
		return lista;
	}

	private Venta getVenta(ResultSet st) throws SQLException, Exception {

		Factura factura=daoFactura.findById(st.getLong("v.id_factura"));
		Usuario usuario=daoUsuario.findById(st.getLong("v.id_usuario"));
		
		Venta venta=new Venta();
		Caja caja=new Caja();
		caja.setId(st.getLong("c.id_caja"));
		caja.setNombre(st.getString("c.nombre"));
		venta.setCaja(caja);
		venta.setFactura(factura);
		venta.setUsuario(usuario);
		
		venta.setItems(daoItem.findByFacturaId(factura.getId()));
		return venta;
	}

	@Override
	public Venta findById(Long id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venta findByString(String name) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Venta object) throws SQLException, Exception {
		String sql="insert into ventas (id_factura,id_caja,id_usuario) values(?,?,?)";
		
		
		try(
				PreparedStatement ps=cnn.prepareStatement(sql))
					{
						ps.setLong(1,object.getFactura().getId() );
						ps.setLong(2,object.getCaja().getId() );
						ps.setLong(3,object.getUsuario().getId() );
						
						ps.executeUpdate();
						
					}
		
	}

	@Override
	public void delete(Venta object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
