package org.facturacion.principal.controllers.ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.facturacion.principal.models.Caja;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.models.Venta;
import org.facturacion.principal.services.ILoginService;
import org.facturacion.principal.services.LoginService;
import org.facturacion.principal.services.cajas.CajasService;
import org.facturacion.principal.services.cajas.ICajasService;
import org.facturacion.principal.services.facturacion.FacturaService;
import org.facturacion.principal.services.facturacion.IFacturaService;
import org.facturacion.principal.utils.GenerarExcel;
import org.facturacion.principal.utils.GenerarPdf;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.ventas.FormVentas;

public class VentasController {

	private FormVentas formVentas;
	public DefaultTableModel modelo;
	private ICajasService cajas;
	private IFacturaService facturas;
	private ILoginService login;

	public VentasController(FormPrincipal principal) {

		try {
			cajas = new CajasService();
			facturas = new FacturaService();
			login = new LoginService();
			formVentas = new FormVentas(principal);
			modelo = new DefaultTableModel();
			cargarTable();
			cargarAcciones();
			iniciar();

			formVentas.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(principal, e.getMessage(), "Error", 0);
		}
	}

	private void iniciar() {
		formVentas.dateChooserFin
				.setDate(Date.from((LocalDate.now()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		formVentas.dateChooserInicio
				.setDate(Date.from((LocalDate.now()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		formVentas.comboBoxCaja.setEnabled(false);
		formVentas.comboBoxUsuario.setEnabled(false);
		formVentas.rdbtnTodos.setSelected(true);
	}

	private void cargarAcciones() {
		formVentas.btnBuscar.addActionListener(ac);
		formVentas.btnExcel.addActionListener(ac);
		formVentas.btnPDf.addActionListener(ac);
		formVentas.rdbtnCaja.addActionListener(ac);
		formVentas.rdbtnTodos.addActionListener(ac);
		formVentas.rdbtnUsuario.addActionListener(ac);

	}

	private void cargarTable() {
		formVentas.table.setModel(modelo);

		modelo.addColumn("Caja");
		modelo.addColumn("Usuario");
		modelo.addColumn("Fecha");
		modelo.addColumn("Monto");
	}

	ActionListener ac = new ActionListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == formVentas.btnBuscar) {
				LocalDate inicio = formVentas.dateChooserInicio.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				LocalDate fin = formVentas.dateChooserFin.getDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				modelo.setRowCount(0);
				if (formVentas.rdbtnCaja.isSelected()) {
					Caja caja = (Caja) formVentas.comboBoxCaja.getSelectedItem();
					if (caja != null) {
						try {
							for (Venta v : facturas.findAllVenta(inicio, fin, caja)) {
								// falta agregar que verifique rango de fechas
								modelo.addRow(new Object[] { v.getCaja().getNombre(), v.getUsuario().getUsername(),
										v.getFactura().getFecha().toString(), v.getTotal().toString() });
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(formVentas, ex.getMessage(), "Error", 0);
						}
					} else {
						JOptionPane.showMessageDialog(formVentas, "No ha seleccionado ninguna caja", "Error", 0);
					}
				} else if (formVentas.rdbtnTodos.isSelected()) {
					try {
						for (Venta v : facturas.findAllVenta(inicio, fin)) {
							// falta agregar que verifique rango de fechas
							modelo.addRow(new Object[] { v.getCaja().getNombre(), v.getUsuario().getUsername(),
									v.getFactura().getFecha().toString(), v.getTotal().toString() });
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(formVentas, ex.getMessage(), "Error", 0);
					}
				} else if (formVentas.rdbtnUsuario.isSelected()) {
					Usuario user = (Usuario) formVentas.comboBoxUsuario.getSelectedItem();
					if (user != null) {
						try {
							for (Venta v : facturas.findAllVenta(inicio, fin, user)) {
								// falta agregar que verifique rango de fechas
								modelo.addRow(new Object[] { v.getCaja().getNombre(), v.getUsuario().getUsername(),
										v.getFactura().getFecha().toString(), v.getTotal().toString() });
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(formVentas, ex.getMessage(), "Error", 0);
						}
					} else {
						JOptionPane.showMessageDialog(formVentas, "No ha seleccionado ningun usuario", "Error", 0);
					}
				}
			}

			if (e.getSource() == formVentas.btnExcel) {
				GenerarExcel.CrearDocumento(modelo);
			}

			if (e.getSource() == formVentas.btnPDf) {
				GenerarPdf.exportarJTableAPDF(formVentas.table);
			}

			if (e.getSource() == formVentas.rdbtnCaja) {
				formVentas.comboBoxCaja.setEnabled(true);
				formVentas.comboBoxUsuario.setEnabled(false);
				try {
					formVentas.comboBoxCaja.removeAllItems();
					for (Caja c : cajas.findAll())
						formVentas.comboBoxCaja.addItem(c);
					formVentas.comboBoxCaja.setSelectedItem(null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(formVentas, ex.getMessage(), "Error", 0);
				}
			}

			if (e.getSource() == formVentas.rdbtnTodos) {
				formVentas.comboBoxCaja.setEnabled(false);
				formVentas.comboBoxUsuario.setEnabled(false);
			}

			if (e.getSource() == formVentas.rdbtnUsuario) {
				formVentas.comboBoxCaja.setEnabled(false);
				formVentas.comboBoxUsuario.setEnabled(true);
				try {
					formVentas.comboBoxUsuario.removeAllItems();
					for (Usuario c : login.findAllUser())
						formVentas.comboBoxUsuario.addItem(c);
					formVentas.comboBoxUsuario.setSelectedItem(null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(formVentas, ex.getMessage(), "Error", 0);
				}
			}

		}
	};

}
