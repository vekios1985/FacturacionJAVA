package org.facturacion.principal.controllers.clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.facturacion.principal.models.Cliente;
import org.facturacion.principal.services.clientes.ClienteService;
import org.facturacion.principal.services.clientes.IClientesService;
import org.facturacion.principal.utils.GenerarExcel;
import org.facturacion.principal.utils.GenerarPdf;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.clientes.FormListadoClientes;

public class ListarClientesController {

	FormPrincipal principal;
	FormListadoClientes frmListado;
	IClientesService service;
	String[] columnas= { "Nombre", "Apellido", "DNI", "Domicilio", "Telefono", "Localidad", "eMail",
	"IVA" };

	public ListarClientesController(FormPrincipal principal) {
		
		try {this.principal = principal;
		frmListado = new FormListadoClientes(principal, true);
		cargarColumnas();
		frmListado.btnBuscar.addActionListener(accion);
		frmListado.btnExcel.addActionListener(accion);
		frmListado.btnPdf.addActionListener(accion);
		frmListado.rdbtnTodos.addActionListener(accion);
		frmListado.rdbtnApellido.addActionListener(accion);
		frmListado.rdbtnDni.addActionListener(accion);
			service = new ClienteService();
			this.frmListado.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
		
	}

	void cargarColumnas() {
	
		for(String s:columnas)
		{
			frmListado.tableModel.addColumn(s);
		}
	}

	ActionListener accion = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == frmListado.rdbtnTodos) {
				frmListado.textFieldBuscar.setEnabled(false);
			}
			if (e.getSource() == frmListado.rdbtnApellido || e.getSource() == frmListado.rdbtnDni) {
				frmListado.textFieldBuscar.setEnabled(true);
			}
			if (e.getSource() == frmListado.btnBuscar) {
				frmListado.tableModel.setRowCount(0);
				if (frmListado.rdbtnTodos.isSelected()) {
					try {
						for (Cliente c : service.findAll()) {
							frmListado.tableModel
									.addRow(new Object[] { c.getNombre(), c.getApellido(), c.getDni(), c.getDireccion(),
											c.getTelefono(), c.getLocalidad(), c.getEmail(), c.getIva().getNombre() });

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frmListado, e1.getMessage(), "Error", 0);
					}
				} else if (frmListado.rdbtnApellido.isSelected()) {
					String apellido = frmListado.textFieldBuscar.getText();
					try {
						for (Cliente c : service.findAll()) {
							if (c.getApellido().toLowerCase().contains(apellido.toLowerCase()))
								frmListado.tableModel.addRow(new Object[] { c.getNombre(), c.getApellido(), c.getDni(),
										c.getDireccion(), c.getTelefono(), c.getLocalidad(), c.getEmail(),
										c.getIva().getNombre() });

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frmListado, e1.getMessage(), "Error", 0);
					}
				} else if (frmListado.rdbtnDni.isSelected()) {
					String sDni = frmListado.textFieldBuscar.getText();
					try {
						for (Cliente c : service.findAll()) {
							if (c.getDni().toString().contains(sDni))
								frmListado.tableModel.addRow(new Object[] { c.getNombre(), c.getApellido(), c.getDni(),
										c.getDireccion(), c.getTelefono(), c.getLocalidad(), c.getEmail(),
										c.getIva().getNombre() });

						}
					} catch (Exception e1) {
					
						JOptionPane.showMessageDialog(frmListado, e1.getMessage(), "Error", 0);
					}
				}
			}

			if (e.getSource() == frmListado.btnExcel) {
				if (frmListado.tableModel.getRowCount() > 0) {
					
					
					GenerarExcel.CrearDocumento(frmListado.tableModel);
				}

			}
			if(e.getSource()==frmListado.btnPdf)
			{
				if(frmListado.tableModel.getRowCount()>0)
				{
					GenerarPdf.exportarJTableAPDF(frmListado.tableClientes);
				}
			}

		}
	};

}
