package org.facturacion.principal.controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.facturacion.principal.controllers.cajas.SeleccionCajaController;
import org.facturacion.principal.models.Caja;
import org.facturacion.principal.models.Cliente;
import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.ItemFactura;
import org.facturacion.principal.models.ItemProducto;
import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.models.Venta;
import org.facturacion.principal.services.clientes.ClienteService;
import org.facturacion.principal.services.clientes.IClientesService;
import org.facturacion.principal.services.facturacion.FacturaService;
import org.facturacion.principal.services.facturacion.IFacturaService;
import org.facturacion.principal.services.productos.IProductoService;
import org.facturacion.principal.services.productos.ProductoService;
import org.facturacion.principal.vista.FormPrincipal;

public class FacturacionController {

	private FormPrincipal principal;

	IProductoService serviceProducto;
	IClientesService serviceCliente;
	IFacturaService serviceFactura;

	List<ItemFactura> listaItems;
	Integer numeracion;
	Venta venta;
	Factura factura;
	Cliente cliente;
	Usuario usuario;
	Caja caja;

	private int itemCount;

	public FacturacionController(FormPrincipal principal, Usuario user) {

		try {
			this.principal = principal;
			caja = null;
			serviceProducto = new ProductoService();
			serviceCliente = new ClienteService();
			serviceFactura = new FacturaService();
			this.usuario = user;
			agregarActionListener();
			cargarTabla();

			iniciarVenta();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}

	}
	
	

	@SuppressWarnings("unchecked")
	void CargarComboFactura() {
		principal.panel.comboBoxTipoFactura.removeAllItems();
		try {
			for (TipoFactura tipo : serviceFactura.findAllTipoFactura()) {
				principal.panel.comboBoxTipoFactura.addItem(tipo);
			}
			principal.panel.comboBoxTipoFactura.setSelectedItem(null);
			principal.panel.lblFechaValor.setText(LocalDate.now().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(principal, e.getMessage(), "Error", 0);
		}
	}

	ItemListener it = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == principal.panel.comboBoxTipoFactura) {
				if (principal.panel.comboBoxTipoFactura.getSelectedIndex() > -1) {
					TipoFactura tipoFactura = (TipoFactura) principal.panel.comboBoxTipoFactura.getSelectedItem();
					try {

						numeracion = serviceFactura.getNumeracion(tipoFactura.getId());
						principal.panel.lblNumeroFacturaValor.setText(numeracion.toString());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(principal, e1.getMessage(), "Error", 0);
					}
				} else {
					principal.panel.lblNumeroFacturaValor.setText("...");
				}
			}

		}
	};

	public void setPanelEnabled(JPanel panel, boolean enabled) {
		panel.setEnabled(enabled); // Esto afecta al propio panel (por ejemplo, en t�rminos visuales).
		for (Component component : panel.getComponents()) {
			component.setEnabled(enabled); // Desactiva todos los hijos del panel.
			if (component instanceof JPanel) {
				// Si hay un JPanel anidado, desactiva tambi�n sus hijos.
				setPanelEnabled((JPanel) component, enabled);
			}
		}
	}

	void iniciarVenta() {
		if (usuario != null) {
			if (caja == null) {
				setPanelEnabled(principal.panel, false);
				principal.panel.btnSeleccionarCaja.setEnabled(true);
			} else {
				principal.panel.lblUsuarioValor.setText(usuario.getUsername());
				setPanelEnabled(principal.panel, true);
				principal.panel.tableModel.setRowCount(0);
				principal.panel.lblSubtotalValor.setText("$0.00");
				principal.panel.lblDescuentoValor.setText("$0.00");
				principal.panel.lblTotalValor.setText("$0.00");
				principal.panel.textArea.setText("");
				itemCount = 0;
				listaItems = new ArrayList<ItemFactura>();
				factura = new Factura();
				factura.setDescuento(0D);
				venta = new Venta();
				venta.setFactura(factura);
				venta.setItems(listaItems);
				CargarComboFactura();
				try {
					cliente = serviceCliente.findByDni(99999999);
					if (cliente == null) {
						principal.panel.lblDniValor.setText("");
						principal.panel.lblNombreYApellidoValor.setText("");
						principal.panel.lblIvaValor.setText("");
					} else {
						principal.panel.lblDniValor.setText(cliente.getDni().toString());
						principal.panel.lblNombreYApellidoValor
								.setText(cliente.getNombre() + " " + cliente.getApellido());
						principal.panel.lblIvaValor.setText(cliente.getIva().getNombre());
					}
					principal.panel.rdbtnDni.setSelected(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("serial")
	void cargarTabla() {

		principal.panel.tableModel.addColumn("#");
		principal.panel.tableModel.addColumn("Codigo");
		principal.panel.tableModel.addColumn("Descripcion");
		principal.panel.tableModel.addColumn("Cantidad");
		principal.panel.tableModel.addColumn("Unitario");
		principal.panel.tableModel.addColumn("Importe");

		principal.panel.table.getColumnModel().getColumn(0).setPreferredWidth(25);
		principal.panel.table.getColumnModel().getColumn(1).setPreferredWidth(80);
		principal.panel.table.getColumnModel().getColumn(2).setPreferredWidth(485);
		principal.panel.table.getColumnModel().getColumn(3).setPreferredWidth(60);
		principal.panel.table.getColumnModel().getColumn(4).setPreferredWidth(100);
		principal.panel.table.getColumnModel().getColumn(5).setPreferredWidth(100);

		// JTableHeader header = tabla.getTableHeader();
		principal.panel.table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				label.setFont(new Font("Tahoma", Font.BOLD, 12)); // Cambiar fuente
				label.setForeground(new Color(255, 255, 255)); // Color de texto
				label.setBackground(new Color(0, 0, 255)); // Fondo azul
				label.setHorizontalAlignment(SwingConstants.CENTER); // Alineaci�n centrada
				return label;
			}
		});
		principal.panel.textFieldCantidad.setText("1");
	}

	private void agregarActionListener() {
		principal.panel.btnBuscar.addActionListener(ac);
		principal.panel.btnCambiarCantidad.addActionListener(ac);
		principal.panel.btnCambiarPrecio.addActionListener(ac);
		principal.panel.btnDescuento.addActionListener(ac);
		principal.panel.btnQuitar.addActionListener(ac);
		principal.panel.btnNuevaVenta.addActionListener(ac);
		principal.panel.btnCobrar.addActionListener(ac);
		principal.panel.comboBoxTipoFactura.addItemListener(it);
		principal.panel.btnBuscarCliente.addActionListener(ac);
		principal.panel.btnSeleccionarCaja.addActionListener(ac);
		// principal.panel.btnCobrar.addActionListener(ac);

	}

	ActionListener ac = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == principal.panel.btnBuscar) {
				try {
					String codigo = principal.panel.textFieldCodigo.getText();
					String cantidad = principal.panel.textFieldCantidad.getText();

					if (codigo.isBlank() || codigo.isEmpty()) {
						throw new Exception("Ingrese un codigo");
					} else {
						if (cantidad.isBlank() || cantidad.isEmpty()) {
							throw new Exception("Ingrese una cantidad");
						} else {
							Long code = Long.parseLong(codigo);
							Double cant = Double.parseDouble(cantidad);
							ItemProducto item = serviceProducto.findItem(code);
							if (item == null) {
								throw new Exception("No hay stock o no existe el producto ingresado");
							} else {
								if (item.getStock() - cant < 0) {
									throw new Exception("No hay suficiente stock del producto ingresado");
								} else {
									item.setStock(item.getStock() - cant);
									ItemFactura itemF = new ItemFactura();
									itemF.setCantidad(cant);
									itemF.setPrecio(item.getPrecio());
									itemF.setProducto(item.getProducto());
									insertarItem(itemF);
									actualizarTabla();
									principal.panel.textFieldCodigo.setText("");
									principal.panel.textFieldCantidad.setText("1");
								}
							}

						}
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(principal, ex.getMessage(), "Error", 0);
				}
			}

			if (e.getSource() == principal.panel.btnQuitar) {
				int row = principal.panel.table.getSelectedRow();
				if (row > -1) {
					try {
						Long codigo = Long.parseLong((String) principal.panel.table.getValueAt(row, 1).toString());
						removerItem(codigo);
						actualizarTabla();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(principal, ex.getMessage(), "Error", 0);
					}
				} else {
					JOptionPane.showMessageDialog(principal, "No ha seleccionado ninguna fila", "Error", 0);
				}

			}

			if (e.getSource() == principal.panel.btnCambiarCantidad) {
				int row = principal.panel.table.getSelectedRow();
				if (row > -1) {
					try {
						Long codigo = Long.parseLong((String) principal.panel.table.getValueAt(row, 1).toString());
						String input = JOptionPane.showInputDialog(null, "Ingresa una nueva cantidad:",
								"Solicitud de valor", JOptionPane.QUESTION_MESSAGE);
						if (input != null) {
							Double cantidad = Double.parseDouble(input);
							if (cantidad > 0) {
								cambiarCantidad(codigo, cantidad);
								actualizarTabla();
							} else {
								throw new Exception("Ha ingresado un cantida invalida");
							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(principal, ex.getMessage(), "Error", 0);
					}
				} else {
					JOptionPane.showMessageDialog(principal, "No ha seleccionado ninguna fila", "Error", 0);
				}
			}

			if (e.getSource() == principal.panel.btnCambiarPrecio) {
				int row = principal.panel.table.getSelectedRow();
				if (row > -1) {
					try {
						Long codigo = Long.parseLong((String) principal.panel.table.getValueAt(row, 1).toString());
						String input = JOptionPane.showInputDialog(null, "Ingresa un nuevo precio:",
								"Solicitud de valor", JOptionPane.QUESTION_MESSAGE);
						if (input != null) {
							Double precio = Double.parseDouble(input);
							if (precio > 0) {
								cambiarPrecio(codigo, precio);
								actualizarTabla();
							} else {
								throw new Exception("Ha ingresado un precio invalido");
							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(principal, ex.getMessage(), "Error", 0);
					}
				} else {
					JOptionPane.showMessageDialog(principal, "No ha seleccionado ninguna fila", "Error", 0);
				}
			}

			if (e.getSource() == principal.panel.btnDescuento) {
				String input = JOptionPane.showInputDialog(null, "Ingresa el porcentaje de descuento",
						"Solicitud de valor", JOptionPane.QUESTION_MESSAGE);
				if (input != null) {
					try {
						Double descuento = Double.parseDouble(input);
						factura.setDescuento(descuento);
						actualizarTabla();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(principal, ex.getMessage(), "Error", 0);
					}
				}

			}

			if (e.getSource() == principal.panel.btnNuevaVenta) {
				iniciarVenta();
				actualizarTabla();
			}

			if (e.getSource() == principal.panel.btnBuscarCliente) {
				if (principal.panel.rdbtnDni.isSelected()) {
					try {
						String input = principal.panel.textFieldCliente.getText();
						if (input != "") {
							Integer dni = Integer.parseInt(input);
							cliente = serviceCliente.findByDni(dni);
							if (cliente != null)

							{
								principal.panel.lblDniValor.setText(cliente.getDni().toString());
								principal.panel.lblNombreYApellidoValor
										.setText(cliente.getNombre() + " " + cliente.getApellido());
								principal.panel.lblIvaValor.setText(cliente.getIva().getNombre());
								principal.panel.textFieldCliente.setText("");
							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(principal, ex.getMessage(), "Error", 0);
					}
				} else if (principal.panel.rdbtnApellido.isSelected()) {
					try {
						String input = principal.panel.textFieldCliente.getText();
						if (input != "") {
							// Integer dni=Integer.parseInt(input);
							cliente = serviceCliente.findByLastName(input);
							if (cliente != null)

							{
								principal.panel.lblDniValor.setText(cliente.getDni().toString());
								principal.panel.lblNombreYApellidoValor
										.setText(cliente.getNombre() + " " + cliente.getApellido());
								principal.panel.lblIvaValor.setText(cliente.getIva().getNombre());
								principal.panel.textFieldCliente.setText("");
							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(principal, ex.getMessage(), "Error", 0);
					}
				}
			}

			if (e.getSource() == principal.panel.btnSeleccionarCaja) {

				SeleccionCajaController cajasController = new SeleccionCajaController(principal);
				caja = cajasController.getCaja();
				if (caja != null) {
					principal.panel.lblCajaValor.setText(caja.getNombre());
					iniciarVenta();
				}
			}

			if (e.getSource() == principal.panel.btnCobrar) {
				if (factura != null && cliente != null && caja != null && usuario != null && venta != null) {
					if (listaItems.isEmpty()) {
						JOptionPane.showMessageDialog(principal, "No hay items cargados para facturar", "Error", 0);
					} else {
						TipoFactura tipo = (TipoFactura) principal.panel.comboBoxTipoFactura.getSelectedItem();
						if (tipo == null) {
							JOptionPane.showMessageDialog(principal, "No ha seleccionado el tipo de factura", "Error",
									0);
						} else {
							factura.setCliente(cliente);
							factura.setFecha(LocalDate.now());
							factura.setNumero(numeracion.toString());
							factura.setTipoFactura(tipo);
							factura.setObservacion(principal.panel.textArea.getText());
							for (ItemFactura item : listaItems) {
								item.setFactura(factura);
							}
							venta.setCaja(caja);
							venta.setItems(listaItems);
							venta.setFactura(factura);
							venta.setUsuario(usuario);
							try {
								serviceFactura.saveFacturaYNumeracion(factura, venta);
								JOptionPane.showMessageDialog(principal, "Se realizo venta con exito", "Exito", 1);
								iniciarVenta();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(principal, e1.getMessage(), "Error", 0);
							}
						}
					}
				} else {
					System.out.println("Falta algo");
				}
			}

		}

	};

	void insertarItem(ItemFactura item) {

		for (int i = 0; i < listaItems.size(); i++) {
			ItemFactura itemExistente = listaItems.get(i);
			if (itemExistente.getProducto().getCodigo().equals(item.getProducto().getCodigo())) {
				// Actualizar la cantidad y salir
				itemExistente.setCantidad(itemExistente.getCantidad() + item.getCantidad());
				listaItems.set(i, itemExistente);
				return;
			}
		}
		listaItems.add(item);
		// actualizarTabla();
	}

	private void cambiarPrecio(Long codigo, Double precio) {
		for (int i = 0; i < listaItems.size(); i++) {
			ItemFactura itemExistente = listaItems.get(i);
			if (itemExistente.getProducto().getCodigo().equals(codigo)) {
				itemExistente.setPrecio(precio);
				listaItems.set(i, itemExistente);
				return;
			}
		}

	}

	void removerItem(Long codigo) {

		for (int i = 0; i < listaItems.size(); i++) {
			ItemFactura itemExistente = listaItems.get(i);
			if (itemExistente.getProducto().getCodigo().equals(codigo)) {
				// Actualizar la cantidad y salir

				listaItems.remove(i);
				return;
			}
		}
		// actualizarTabla();
	}

	void cambiarCantidad(Long codigo, Double cantidad) {
		for (int i = 0; i < listaItems.size(); i++) {
			ItemFactura itemExistente = listaItems.get(i);
			if (itemExistente.getProducto().getCodigo().equals(codigo)) {
				itemExistente.setCantidad(cantidad);
				listaItems.set(i, itemExistente);
				return;
			}
		}
	}

	private void actualizarTabla() {
		itemCount = 0;
		principal.panel.tableModel.setRowCount(0); // Limpiar la tabla
		for (ItemFactura item : listaItems) {
			itemCount++;
			principal.panel.tableModel.addRow(new Object[] { itemCount, item.getProducto().getCodigo(),
					item.getProducto().getNombre(), item.getCantidad(), item.getPrecio(), item.getMonto() });
		}
		Double subtotal = venta.getSubTotal();
		Double descuento = subtotal * factura.getDescuento() / 100;
		principal.panel.lblSubtotalValor.setText("$" + subtotal.toString());
		principal.panel.lblDescuentoValor.setText("$" + descuento);
		principal.panel.lblTotalValor.setText("$" + venta.getTotal().toString());
	}
}
