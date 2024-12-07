package org.facturacion.principal.controllers.productos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import org.facturacion.principal.models.ItemProducto;
import org.facturacion.principal.models.Producto;
import org.facturacion.principal.models.Proveedor;
import org.facturacion.principal.services.productos.IProductoService;
import org.facturacion.principal.services.productos.ProductoService;
import org.facturacion.principal.services.proveedores.IProveedorService;
import org.facturacion.principal.services.proveedores.ProveedorService;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.productos.FormIngresarStock;

public class StockController {
	
	private FormIngresarStock formStock;
	private IProductoService serviceProducto;
	private IProveedorService serviceProveedor;
	private ItemProducto item=null;
	
	
	public StockController(FormPrincipal principal) {
		try {
			formStock=new FormIngresarStock(principal, true);
			serviceProducto=new ProductoService();
			serviceProveedor=new ProveedorService();
			CargarCombos();
			formStock.comboBoxProducto.addItemListener(it);
			formStock.comboBoxProveedor.addItemListener(it);
			formStock.btnAgregar.addActionListener(ac);
			formStock.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void CargarCombos()
	{
		try {
			
			DefaultComboBoxModel<Producto> modeloProductos=new DefaultComboBoxModel<Producto>();
			DefaultComboBoxModel<Proveedor>modeloProveedor=new DefaultComboBoxModel<Proveedor>();
			for(Producto p:serviceProducto.findAllProductos())
				modeloProductos.addElement(p);
			for(Proveedor p:serviceProveedor.findAll())
			{
				modeloProveedor.addElement(p);
			}
			formStock.comboBoxProducto.setModel(modeloProductos);
			formStock.comboBoxProveedor.setModel(modeloProveedor);
			formStock.comboBoxProducto.setSelectedItem(null);
			formStock.comboBoxProveedor.setSelectedItem(null);
		
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	ItemListener it=new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(formStock.comboBoxProducto.getSelectedItem()!=null&&formStock.comboBoxProveedor.getSelectedItem()!=null)
			{
				Producto producto=(Producto) formStock.comboBoxProducto.getSelectedItem();
				Proveedor proveedor=(Proveedor) formStock.comboBoxProveedor.getSelectedItem();
				
				try {
					item=serviceProducto.findItem(producto, proveedor);
					if(item.getPrecio()==0&&item.getStock()==0)
					{
						formStock.lblInfo.setText("El producto no esta registrado aun con stock ni precio");
					}
					else
					{
						formStock.lblInfo.setText("<html>Producto registrado<br>Stock: " + item.getStock() +
		                          "<br>Precio: " + item.getPrecio() +
		                          "<br>Ingrese nuevo precio y las unidades a ingresar</html>");
						
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formStock, e1.getMessage(), "Error", 0);
				}
			}
		}
	};
	
	ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==formStock.btnAgregar)
			{
				try
				{
					if(formStock.comboBoxProducto.getSelectedItem()!=null&&formStock.comboBoxProveedor.getSelectedItem()!=null)
					{
						//Producto producto=(Producto) formStock.comboBoxProducto.getSelectedItem();
						//Proveedor proveedor=(Proveedor) formStock.comboBoxProveedor.getSelectedItem();
						Double precio=Double.parseDouble(formStock.textFieldPrecio.getText());
						Double stock=Double.parseDouble(formStock.textFieldStock.getText());
						if(precio<0||stock<0)
						{
							JOptionPane.showMessageDialog(formStock, "El precio y stock no pueden ser menor a 0", "Error", 0);
							
						}else {
						item.setPrecio(precio);
						item.setStock(item.getStock()+stock);
						serviceProducto.saveItem(item);
						JOptionPane.showMessageDialog(formStock, "Stock y precio actualizados con exito", "Exito", 1);
						CargarCombos();
						formStock.textFieldPrecio.setText("");
						formStock.textFieldStock.setText("");
						formStock.lblInfo.setText("...");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(formStock, "Debe seleccionar producto y proveedor", "Error", 0);
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(formStock, "Existe un error en los valores de precio y stock", "Error", 0);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(formStock, ex.getMessage(), "Error", 0);
				}
			}
			
		}
	};

}
