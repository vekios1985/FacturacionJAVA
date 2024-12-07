package org.facturacion.principal.controllers.proveedores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;


import org.facturacion.principal.models.Proveedor;
import org.facturacion.principal.services.proveedores.IProveedorService;
import org.facturacion.principal.services.proveedores.ProveedorService;
import org.facturacion.principal.utils.GenerarExcel;
import org.facturacion.principal.utils.GenerarPdf;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.proveedores.FormListarProveedores;

public class ListarProveedoresController {

	FormListarProveedores proveedores;
	private IProveedorService service;

	private String[] columnas = { "Nombre", "Telefono", "eMail", "Dirección", "CUIT" };

	public ListarProveedoresController(FormPrincipal princiapl) {
		try {
			proveedores = new FormListarProveedores(princiapl, true);
			inicio();
			service = new ProveedorService();
			proveedores.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
		// TODO Auto-generated constructor stub
	}

	private void inicio() {
		proveedores.textFieldBuscar.setEnabled(false);
		proveedores.btnExcel.addActionListener(ac);
		proveedores.btnListar.addActionListener(ac);
		proveedores.btnPdf.addActionListener(ac);
		proveedores.rdbtncuit.addActionListener(ac);
		proveedores.rdbtnNombre.addActionListener(ac);
		proveedores.rdbtnTodos.addActionListener(ac);

		for (String s : columnas) {
			proveedores.modelo.addColumn(s);
		}

	}

	private ActionListener ac = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == proveedores.rdbtnTodos) {
				proveedores.textFieldBuscar.setEnabled(false);
			}
			if (e.getSource() == proveedores.rdbtncuit) {
				proveedores.textFieldBuscar.setEnabled(true);
			}
			if (e.getSource() == proveedores.rdbtnNombre) {
				proveedores.textFieldBuscar.setEnabled(true);
			}
			if (e.getSource() == proveedores.btnListar) {
				proveedores.modelo.setRowCount(0);
				try {
					List<Proveedor> lista = null;
					if (proveedores.rdbtnTodos.isSelected()) {
						lista = service.findAll();

					} else if (proveedores.rdbtncuit.isSelected()) {
						String cuit = proveedores.textFieldBuscar.getText();
						lista = service.ListByCuit(cuit);
					} else if (proveedores.rdbtnNombre.isSelected()) {
						String name = proveedores.textFieldBuscar.getText().toLowerCase();
						lista = service.ListByName(name);
					}
					for (Proveedor p : lista) {
						Object[] linea = new Object[] { p.getNombre(), p.getTelefono(), p.getEmail(), p.getDireccion(),
								p.getCuit() };
						proveedores.modelo.addRow(linea);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(proveedores, ex.getMessage(), "Error", 0);
				}
			}
			if (e.getSource() == proveedores.btnExcel) {
				if(proveedores.modelo.getRowCount()>0)
				
					GenerarExcel.CrearDocumento(proveedores.modelo);
				
			}
			if(e.getSource()==proveedores.btnPdf)
			{
				if(proveedores.modelo.getRowCount()>0)
				GenerarPdf.exportarJTableAPDF(proveedores.table);
			}
		}
	};

}
