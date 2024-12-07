package org.facturacion.principal.controllers.cajas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.facturacion.principal.models.Caja;
import org.facturacion.principal.services.cajas.CajasService;
import org.facturacion.principal.services.cajas.ICajasService;
import org.facturacion.principal.utils.Texto;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.cajas.FormNuevaCaja;

public class AltaCajasController {
	
	FormNuevaCaja nuevaCaja;
	ICajasService service;
	DefaultListModel<Caja> modelo;
	
	@SuppressWarnings("unchecked")
	public AltaCajasController(FormPrincipal principal) {
		
		
		try {nuevaCaja=new FormNuevaCaja(principal, true);
			service = new CajasService();
			modelo = new DefaultListModel<Caja>();
			nuevaCaja.list.setModel(modelo);
			listarCajas();
			nuevaCaja.btnAgregar.addActionListener(ac);

			nuevaCaja.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
		
	}
	
	void listarCajas()
	{
		modelo.clear();
		try {
			for(Caja c:service.findAll())
			{
			modelo.addElement(c);	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(nuevaCaja, e.getMessage(), "Error", 0);
		}
	}
	
	ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==nuevaCaja.btnAgregar)
			{
				try {
					String input=Texto.normalizar(nuevaCaja.textFieldNombreCaja.getText());
					if(input!="")
					{
						service.save(input);
						listarCajas();
						nuevaCaja.textFieldNombreCaja.setText("");
						JOptionPane.showMessageDialog(nuevaCaja, "Se ingreso la caja con exito", "Exito", 0);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(nuevaCaja, e1.getMessage(), "Error", 0);
				}
				
				
			}
			
		}
	};

}
