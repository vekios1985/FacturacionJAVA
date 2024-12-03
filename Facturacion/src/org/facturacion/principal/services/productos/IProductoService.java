package org.facturacion.principal.services.productos;

import java.util.List;

import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.models.ItemProducto;
import org.facturacion.principal.models.Producto;
import org.facturacion.principal.models.Proveedor;

public interface IProductoService {
	
	public List<Categoria> findAllCategorias()throws Exception;
	
	public void saveCategoria(Categoria categoria)throws Exception;
	
	public List<Producto> findAllProductos()throws Exception;
	
	public List<Producto> findAllByCategoria(Categoria categoria)throws Exception;
	
	public Producto findByCodigo(Long codigo)throws Exception;
	
	public List<Producto> findByName(String name)throws Exception;
	
	public void saveProducto(Producto producto)throws Exception;
	
	public ItemProducto findItem(Producto producto,Proveedor proveedor)throws Exception;
	
	public ItemProducto findItem(Long codigo)throws Exception;
	
	public List<ItemProducto> findAllItems()throws Exception;
	
	public List<ItemProducto> findItemsByName(String name)throws Exception;
	
	public List<ItemProducto> findItemsByCategoria(Categoria categoria)throws Exception;
	
	public List<ItemProducto> findItemsByProveedor(Proveedor proveedor)throws Exception;
	
	public void saveItem(ItemProducto item)throws Exception;
	

}
