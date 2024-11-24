package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.Caja;
import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.ItemFactura;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.models.Venta;
import org.facturacion.principal.utils.Conexion;

public class DaoVentas implements IDao<Venta>{
	
	private DaoUsuario daoUsuario;
	private DaoFactura daoFactura;
	private DaoItemFactura daoItem;
	public DaoVentas()
	{
		daoFactura=new DaoFactura();
		daoUsuario=new DaoUsuario();
		daoItem=new DaoItemFactura();
	}

	@Override
	public List<Venta> findAll() throws SQLException, Exception {
		List<Venta>lista=new ArrayList<Venta>();
		try(Connection cnn=Conexion.getConnection();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Venta object) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
