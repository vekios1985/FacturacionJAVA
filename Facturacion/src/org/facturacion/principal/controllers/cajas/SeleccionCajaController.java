package org.facturacion.principal.controllers.cajas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.facturacion.principal.models.Caja;
import org.facturacion.principal.services.cajas.CajasService;
import org.facturacion.principal.services.cajas.ICajasService;
import org.facturacion.principal.vista.cajas.FormSeleccionCaja;

public class SeleccionCajaController {
	
	private ICajasService service;
	private FormSeleccionCaja cajas;
	Caja caja;
	
	public SeleccionCajaController(JFrame frame) {
		caja=null;
		try {
			service=new CajasService();
			cajas=new FormSeleccionCaja(frame);
		CargarLista();
		cajas.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
		
	}

	@SuppressWarnings({ "unchecked" })
	private void CargarLista()
	{
		DefaultListModel<Caja>modelo=new DefaultListModel<Caja>();
		cajas.list.setModel(modelo);
		try {
			for(Caja c:service.findAll())
			{
				modelo.addElement(c);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(cajas, e.getMessage(), "Error", 0);
		}
		
		 cajas.okButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                caja = (Caja) cajas.list.getSelectedValue();
	                cajas.dispose(); // Cierra el diálogo.
	            }
	        });

	        cajas.cancelButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                caja = null; // No se selecciona ninguna caja.
	                cajas.dispose(); // Cierra el diálogo.
	            }
	        });
	    }
	
	

	
	public Caja getCaja()
	{
		return caja;
	}
}

