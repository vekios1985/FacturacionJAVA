package org.facturacion.principal.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
	
	public List<T> findAll()throws SQLException, Exception;
	public T findById(Long id)throws SQLException, Exception;
	public T findByString(String name)throws SQLException, Exception;
	public void save(T object)throws SQLException, Exception;
	public void delete(T object)throws SQLException, Exception;
	
	

}
