package org.facturacion.principal.controllers.productos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.models.Producto;
import org.facturacion.principal.services.productos.IProductoService;
import org.facturacion.principal.services.productos.ProductoService;
import org.facturacion.principal.utils.Texto;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.productos.FormCargarProducto;

public class CargarProductoController {
	
	private IProductoService service;	
	private FormCargarProducto formProducto;
	boolean modificar=false;
	private Producto producto=null;
	
	public CargarProductoController(FormPrincipal principal,boolean modificar) {
		try {
			formProducto=new FormCargarProducto(principal, true);
			this.modificar=modificar;
			service=new ProductoService();
			CargarComboBox();
			formProducto.btnAgregar.addActionListener(ac);
			formProducto.btnBuscar.addActionListener(ac);
			formProducto.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
	}
	
	@SuppressWarnings("unchecked")
	void CargarComboBox()
	{
		if(modificar)
		{
			formProducto.btnBuscar.setVisible(true);
			formProducto.btnAgregar.setText("Guardar");
			formProducto.textFieldNombre.setEnabled(false);
			formProducto.comboBoxCategoria.setEnabled(false);
		}
		else
		{
			formProducto.btnBuscar.setVisible(false);
			formProducto.btnAgregar.setText("Agregar");
			formProducto.textFieldNombre.setEnabled(true);
			formProducto.comboBoxCategoria.setEnabled(true);
		}
		formProducto.comboBoxCategoria.removeAllItems();
		try {
			
			DefaultComboBoxModel<Categoria> modelo=new DefaultComboBoxModel<Categoria>();
			for(Categoria c:service.findAllCategorias())
				modelo.addElement(c);
			formProducto.comboBoxCategoria.setModel(modelo);
	        formProducto.comboBoxCategoria.setSelectedItem(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(formProducto, e.getMessage(), "Error", 0);
		}
	}
	
	ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==formProducto.btnAgregar)
			{
				try {
					String nombre=Texto.normalizar(formProducto.textFieldNombre.getText());
					Long codigo=Long.parseLong(formProducto.textFieldCodigo.getText());
					Categoria categoria=(Categoria)formProducto.comboBoxCategoria.getSelectedItem();
					if(categoria==null)
					{
						throw new Exception("Debe seleccionar una categoria");
					}
					else
					{
						if(codigo<=0)
						{
							throw new Exception("El codigo ingresado no es valido");
						}
						else
						{
							if(producto==null)
								producto=new Producto(nombre, categoria, codigo);
							else {
								producto.setNombre(nombre);
								producto.setCategoria(categoria);
							}
							service.saveProducto(producto);
							formProducto.textFieldCodigo.setText("");
							formProducto.textFieldNombre.setText("");
							formProducto.comboBoxCategoria.setSelectedItem(null);
							JOptionPane.showMessageDialog(formProducto, "Producto guardado con exito", "Exito", 0);
							producto=null;
							CargarComboBox();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formProducto, e1.getMessage(), "Error", 0);
				}
				
			}
			
			if(e.getSource()==formProducto.btnBuscar)
			{
				try
				{
					String n_codigo=formProducto.textFieldCodigo.getText();
					if(n_codigo.isBlank()||n_codigo.isEmpty())
						throw new Exception ("El campo de codigo esta vacío");
					Long codigo=Long.parseLong(formProducto.textFieldCodigo.getText());
					
					
					producto=service.findByCodigo(codigo);
					if(producto!=null)
						
					{
						formProducto.textFieldNombre.setEnabled(true);
						formProducto.comboBoxCategoria.setEnabled(true);
						formProducto.textFieldNombre.setText(producto.getNombre());
						formProducto.comboBoxCategoria.setSelectedItem(producto.getCategoria());
					}
					else
					{
						throw new Exception("Producto no encontrado");
					}
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(formProducto, ex.getMessage(), "Error", 0);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(formProducto, ex.getMessage(), "Error", 0);
				}
			}
			
		}
	};

}
