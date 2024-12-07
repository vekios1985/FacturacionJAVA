package org.facturacion.principal.controllers.productos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;

import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.services.productos.IProductoService;
import org.facturacion.principal.services.productos.ProductoService;
import org.facturacion.principal.utils.Texto;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.administracion.FormAltaCategorias;

public class CategoriasController {
	
	FormAltaCategorias formCategoria;
	private IProductoService service;
	
	
	public CategoriasController(FormPrincipal principal)
	{
		try {
			formCategoria=new FormAltaCategorias(principal, true);
			formCategoria.btnGuardar.addActionListener(ac);
			service=new ProductoService();
			ListarCategorias();
			
			
			formCategoria.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
	}
	
	
	ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==formCategoria.btnGuardar)
			{
				try {
					String s_categoria=Texto.normalizar(formCategoria.textFieldCategoria.getText());
					Categoria categoria=new Categoria();
					categoria.setNombre(s_categoria);
					service.saveCategoria(categoria);
					ListarCategorias();
					formCategoria.textFieldCategoria.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formCategoria, e1.getMessage(), "Error", 0);
				}
				
			}
			
		}
	};
	
	
	
	void ListarCategorias()
	{
		try {
			formCategoria.modelo.removeAllElements();
			for(Categoria cat:service.findAllCategorias())
			{
				
				formCategoria.modelo.addElement(cat);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(formCategoria, e.getMessage(), "Error", 0);
		}
	}

}
