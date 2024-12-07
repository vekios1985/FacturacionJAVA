package org.facturacion.principal.controllers.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.facturacion.principal.models.Iva;
import org.facturacion.principal.services.clientes.ClienteService;
import org.facturacion.principal.utils.Texto;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.administracion.FormAltaIva;

public class AltaIvaController {

	@SuppressWarnings("unused")
	private FormPrincipal principal;
	private FormAltaIva altaIva;
	private ClienteService service;

	public AltaIvaController(FormPrincipal principal) {

		try {
			this.principal = principal;
			this.altaIva = new FormAltaIva(principal, true);
			service = new ClienteService();
			this.altaIva.btnGuardar.addActionListener(accion);
			this.altaIva.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}

	}

	ActionListener accion = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == altaIva.btnGuardar) {
				try {
					String texto = Texto.normalizar(altaIva.textFieldIva.getText());
					Iva iva = new Iva();
					iva.setNombre(texto);
					service.guardarIva(iva);
					altaIva.textFieldIva.setText("");
					JOptionPane.showMessageDialog(altaIva, "Iva registrado con exito", "Exito", 0);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(altaIva, ex.getMessage(), "Error", 0);
				}

			}

		}
	};

}
