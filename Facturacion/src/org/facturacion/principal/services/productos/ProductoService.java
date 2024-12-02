package org.facturacion.principal.services.productos;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.facturacion.principal.dao.DaoCategoria;
import org.facturacion.principal.dao.DaoItemProducto;
import org.facturacion.principal.dao.DaoProducto;
import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.models.ItemProducto;
import org.facturacion.principal.models.Producto;
import org.facturacion.principal.models.Proveedor;

public class ProductoService implements IProductoService{
	
	private DaoCategoria daoCategoria;
	private DaoProducto daoProducto;
	private DaoItemProducto daoItem;
	
	public ProductoService()
	{
		daoCategoria=new DaoCategoria();
		daoProducto=new DaoProducto();
		daoItem=new DaoItemProducto();
	}

	@Override
	public List<Categoria> findAllCategorias() throws Exception {
		return daoCategoria.findAll();
	}

	@Override
	public void saveCategoria(Categoria categoria) throws Exception {

		try
		{
			daoCategoria.save(categoria);
		}
		catch(SQLIntegrityConstraintViolationException ex)
		{
			throw new Exception("La categoria ingresada ya exite");
		}
		
	}

	@Override
	public List<Producto> findAllProductos() throws Exception {
		return daoProducto.findAll();
	}

	@Override
	public List<Producto> findAllByCategoria(Categoria categoria) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto findByCodigo(Long codigo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveProducto(Producto producto) throws Exception {
		try
		{
			daoProducto.save(producto);
		}
		catch(SQLIntegrityConstraintViolationException ex)
		{
			throw new Exception ("El codigo ingresado ya esta registrado en la Base de datos");
		}
		
	}

	@Override
	public ItemProducto findItem(Producto producto, Proveedor proveedor) throws Exception {
		List<ItemProducto>lista=daoItem.findAll();
		Optional<ItemProducto> item=lista.stream().filter(i->
			i.getProducto().getId().equals(producto.getId())&&i.getProveedor().getId().equals(proveedor.getId())).findFirst();
		if(item.isPresent())
		{
			return item.get();
		}
		else
			return new ItemProducto(producto,proveedor,0D,0D);
	}

	@Override
	public void saveItem(ItemProducto item) throws Exception {
		daoItem.save(item);
		
	}

	@Override
	public List<ItemProducto> findAllItems() throws Exception {
		return daoItem.findAll();
	}

	@Override
	public List<ItemProducto> findItemsByName(String name) throws Exception {
		List<ItemProducto>lista=new ArrayList<ItemProducto>();
		for(ItemProducto item:findAllItems())
		{
			if(item.getProducto().getNombre().toLowerCase().contains(name.toLowerCase()))
				lista.add(item);
		}
		return lista;
	
	}

	@Override
	public List<ItemProducto> findItemsByCategoria(Categoria categoria) throws Exception {
		List<ItemProducto>lista=new ArrayList<ItemProducto>();
		for(ItemProducto item:findAllItems())
		{
			if(item.getProducto().getCategoria().getId().equals(categoria.getId()))
				lista.add(item);
		}
		return lista;
	}

	@Override
	public List<ItemProducto> findItemsByProveedor(Proveedor proveedor) throws Exception {
		List<ItemProducto>lista=new ArrayList<ItemProducto>();
		for(ItemProducto item:findAllItems())
		{
			if(item.getProveedor().getId().equals(proveedor.getId()))
				lista.add(item);
		}
		return lista;
	}

}
