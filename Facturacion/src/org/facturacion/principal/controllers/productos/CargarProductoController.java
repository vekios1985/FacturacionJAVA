package org.facturacion.principal.controllers.productos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.models.Producto;
import org.facturacion.principal.services.productos.IProductoService;
import org.facturacion.principal.services.productos.ProductoService;
import org.facturacion.principal.utils.AutocompleteComboBox;
import org.facturacion.principal.utils.Texto;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.productos.FormCargarProducto;

public class CargarProductoController {
	
	private IProductoService service;	
	private FormCargarProducto formProducto;
	
	public CargarProductoController(FormPrincipal principal) {
		formProducto=new FormCargarProducto(principal, true);
		service=new ProductoService();
		CargarComboBox();
		formProducto.btnAgregar.addActionListener(ac);
		formProducto.setVisible(true);
	}
	
	void CargarComboBox()
	{
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
							Producto p=new Producto(nombre, categoria, codigo);
							service.saveProducto(p);
							formProducto.textFieldCodigo.setText("");
							formProducto.textFieldNombre.setText("");
							formProducto.comboBoxCategoria.setSelectedItem(null);
							JOptionPane.showMessageDialog(formProducto, "Producto guardado con exito", "Exito", 0);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formProducto, e1.getMessage(), "Error", 0);
				}
				
			}
			
		}
	};

}
