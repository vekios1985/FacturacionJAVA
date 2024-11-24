package org.facturacion.principal.dao;

import java.util.List;

public interface IDao<T> {
	
	public List<T> findAll();
	public T findById(Long id);
	public T findByString(String name);
	public void save(T object);
	public void delete(T object);
	
	

}
