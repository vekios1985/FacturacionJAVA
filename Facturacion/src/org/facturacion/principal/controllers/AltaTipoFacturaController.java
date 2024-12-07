package org.facturacion.principal.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import org.facturacion.principal.services.facturacion.FacturaService;
import org.facturacion.principal.services.facturacion.IFacturaService;
import org.facturacion.principal.utils.Texto;

public class AltaTipoFacturaController {
	
	private IFacturaService service;
	
	public AltaTipoFacturaController() {
		try {
			service=new FacturaService();
			Ingresar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
		
		
	}
	
	void Ingresar()
	{
		String input = JOptionPane.showInputDialog(null, "Ingresa un nuevo tipo de factura:", "Solicitud de valor", JOptionPane.QUESTION_MESSAGE);
		try
		{
			String tipo=Texto.normalizar(input);
			service.saveTipoFactura(tipo);
		}
		catch(SQLIntegrityConstraintViolationException sql)
		{
			JOptionPane.showMessageDialog(null, "Ya existe ese valor", "Error", 0);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
	}

}
