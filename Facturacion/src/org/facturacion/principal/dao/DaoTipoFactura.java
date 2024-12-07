package org.facturacion.principal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.facturacion.principal.models.TipoFactura;


public class DaoTipoFactura implements IDao<TipoFactura> {

	//private DaoNumeracion numeracion;
	
	private Connection cnn;
	
	public DaoTipoFactura(Connection con)
	{
		this.cnn=con;
	}

	

	@Override
	public List<TipoFactura> findAll() throws SQLException, Exception {
		List<TipoFactura> lista = new ArrayList<TipoFactura>();
		try (
				ResultSet st = cnn.createStatement().executeQuery("select * from tipos_facturas")) {
			while (st.next()) {

				TipoFactura tipo = new TipoFactura();
				tipo.setId(st.getLong("id_tipo_factura"));
				tipo.setTipo(st.getString("tipo_factura"));
				lista.add(tipo);
			}
		}

		return lista;
	}

	@Override
	public TipoFactura findById(Long id) throws SQLException, Exception {
		TipoFactura p = null;
		try (
				PreparedStatement ps = cnn.prepareStatement("select * from tipos_facturas where p.id_tipo_factura=?")) {
			ps.setLong(1, id);
			ResultSet st = ps.executeQuery();
			if (st.next()) {
				p = new TipoFactura();
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

		
		try {
			cnn.setAutoCommit(false);
			PreparedStatement ps = cnn.prepareStatement("insert into tipos_facturas (tipo_factura) values(?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, object.getTipo());
			int lastId = ps.executeUpdate();

			if (lastId > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					Long idGenerado = rs.getLong(1);

					PreparedStatement st=cnn.prepareStatement("insert into secuencia_facturas (secuencia,id_tipo_factura)values(?,?)");
					{
						st.setInt(1, 0);
						st.setLong(2, idGenerado);
						st.executeUpdate();
					}

					cnn.commit();
				} else {
					cnn.rollback();
				}
			} else {
				cnn.rollback();
			}

		} catch (SQLException | NumberFormatException ex) {
			cnn.rollback();
			throw ex;
		} finally {
			cnn.setAutoCommit(true);
		}
	}

	@Override
	public void delete(TipoFactura object) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

}
