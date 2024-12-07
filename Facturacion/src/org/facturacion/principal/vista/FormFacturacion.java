package org.facturacion.principal.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class FormFacturacion extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldCodigo;
	public JTextField textFieldCantidad;
	public JLabel lblCodigo;
	public JLabel lblCantidad;
	public JButton btnBuscar;
	public DefaultTableModel tableModel;
	public JTable table;
	public JButton btnQuitar;
	public JButton btnDescuento;
	public JButton btnCambiarPrecio;
	public JButton btnCambiarCantidad;
	public JLabel lblSubtotal;
	public JLabel lblSubtotalValor;
	public JLabel lblDescuento;
	public JLabel lblDescuentoValor;
	public JLabel lblTotal;
	public JLabel lblTotalValor;
	public JButton btnCobrar;
	public JButton btnNuevaVenta;
	private JPanel panel_1;
	private JPanel panel_2;
	public JLabel lblCliente;
	public JLabel lblFactura;
	private JLabel lblFecha;
	public JLabel lblObservacion;
	public JLabel lblFechaValor;
	public JLabel lblTipoFactura;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxTipoFactura;
	public JLabel lblNumero;
	public JLabel lblNumeroFacturaValor;
	public JTextArea textArea;
	public JTextField textFieldCliente;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JLabel lblNombreYApellidoValor;
	public JLabel lblDniValor;
	public JLabel lblIvaValor;
	public JButton btnBuscarCliente;
	public JLabel lblNombreYApellido;
	public JLabel lblDNI;
	public JLabel lblva;
	public JRadioButton rdbtnDni;
	public JRadioButton rdbtnApellido;
	public JButton btnSeleccionarCaja;
	public JLabel lblCajaValor;
	public JLabel lblCaja;
	public JLabel lblUsuario;
	public JLabel lblUsuarioValor;
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("rawtypes")
	public FormFacturacion() {
		setLayout(null);
		this.setPreferredSize(new Dimension(1270,610));
		
		lblCodigo = new JLabel(" Codigo");
		lblCodigo.setOpaque(true);
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setForeground(new Color(255, 255, 255));
		lblCodigo.setBackground(new Color(0, 0, 255));
		lblCodigo.setBounds(10, 13, 165, 20);
		add(lblCodigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(10, 33, 165, 20);
		add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		lblCantidad = new JLabel(" Cantidad");
		lblCantidad.setOpaque(true);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidad.setBackground(new Color(0, 0, 255));
		lblCantidad.setForeground(new Color(255, 255, 255));
		lblCantidad.setBounds(185, 13, 86, 20);
		add(lblCantidad);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(185, 33, 86, 20);
		add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		btnBuscar = new JButton("Agregar");
		btnBuscar.setBounds(281, 32, 89, 23);
		add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 850, 250);
		add(scrollPane);
		
		tableModel=new DefaultTableModel();
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(10, 331, 89, 23);
		add(btnQuitar);
		
		btnDescuento = new JButton("Descuento");
		btnDescuento.setBounds(109, 331, 89, 23);
		add(btnDescuento);
		
		btnCambiarPrecio = new JButton("Cambiar precio");
		btnCambiarPrecio.setBounds(732, 331, 128, 23);
		add(btnCambiarPrecio);
		
		btnCambiarCantidad = new JButton("Cambiar cantidad");
		btnCambiarCantidad.setBounds(594, 331, 128, 23);
		add(btnCambiarCantidad);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(870, 13, 390, 256);
		add(panel);
		panel.setLayout(null);
		
		lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSubtotal.setForeground(new Color(0, 0, 255));
		lblSubtotal.setBounds(10, 11, 122, 51);
		panel.add(lblSubtotal);
		
		lblSubtotalValor = new JLabel("$0,00");
		lblSubtotalValor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubtotalValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtotalValor.setForeground(new Color(0, 0, 255));
		lblSubtotalValor.setBounds(258, 11, 122, 51);
		panel.add(lblSubtotalValor);
		
		lblDescuento = new JLabel("Descuento");
		lblDescuento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento.setForeground(new Color(255, 255, 255));
		lblDescuento.setBounds(10, 73, 122, 43);
		panel.add(lblDescuento);
		
		lblDescuentoValor = new JLabel("$0,00");
		lblDescuentoValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuentoValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescuentoValor.setForeground(new Color(255, 255, 255));
		lblDescuentoValor.setBounds(258, 73, 122, 43);
		panel.add(lblDescuentoValor);
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setForeground(new Color(0, 0, 255));
		lblTotal.setBounds(10, 182, 112, 80);
		panel.add(lblTotal);
		
		lblTotalValor = new JLabel("$0,00");
		lblTotalValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalValor.setForeground(new Color(0, 0, 255));
		lblTotalValor.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTotalValor.setBounds(132, 182, 248, 80);
		panel.add(lblTotalValor);
		
		btnCobrar = new JButton("Cobrar");
		//btnCobrar.setOpaque(false);
		btnCobrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		//btnCobrar.setContentAreaFilled(false);
		btnCobrar.setBackground(new Color(0, 0, 255));
		btnCobrar.setBounds(1078, 280, 182, 40);
		add(btnCobrar);
		
		btnNuevaVenta = new JButton("Nueva Venta");
		btnNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNuevaVenta.setBackground(new Color(0, 0, 255));
		btnNuevaVenta.setBounds(870, 280, 182, 40);
		add(btnNuevaVenta);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 365, 850, 120);
		add(panel_1);
		panel_1.setLayout(null);
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setBackground(new Color(0, 0, 255));
		lblCliente.setForeground(new Color(255, 255, 255));
		lblCliente.setOpaque(true);
		lblCliente.setBounds(0, 0, 89, 22);
		panel_1.add(lblCliente);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(10, 71, 120, 20);
		panel_1.add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setBounds(167, 70, 89, 23);
		panel_1.add(btnBuscarCliente);
		
		lblNombreYApellido = new JLabel("Nombre y Apellido");
		lblNombreYApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreYApellido.setBounds(329, 21, 156, 22);
		panel_1.add(lblNombreYApellido);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDNI.setBounds(329, 46, 156, 23);
		panel_1.add(lblDNI);
		
		lblva = new JLabel("Condicion IVA");
		lblva.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblva.setBounds(329, 74, 156, 22);
		panel_1.add(lblva);
		
		rdbtnDni = new JRadioButton("DNI");
		rdbtnDni.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGroup.add(rdbtnDni);
		rdbtnDni.setBounds(10, 29, 109, 23);
		panel_1.add(rdbtnDni);
		
		rdbtnApellido = new JRadioButton("Apellido");
		buttonGroup.add(rdbtnApellido);
		rdbtnApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnApellido.setBounds(167, 29, 109, 23);
		panel_1.add(rdbtnApellido);
		
		lblNombreYApellidoValor = new JLabel("...");
		lblNombreYApellidoValor.setBounds(495, 26, 345, 14);
		panel_1.add(lblNombreYApellidoValor);
		
		lblDniValor = new JLabel("...");
		lblDniValor.setBounds(495, 51, 345, 14);
		panel_1.add(lblDniValor);
		
		lblIvaValor = new JLabel("...");
		lblIvaValor.setBounds(495, 74, 345, 14);
		panel_1.add(lblIvaValor);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 486, 850, 120);
		add(panel_2);
		panel_2.setLayout(null);
		
		lblFactura = new JLabel("Factura");
		lblFactura.setOpaque(true);
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setForeground(Color.WHITE);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFactura.setBackground(Color.BLUE);
		lblFactura.setBounds(0, 0, 89, 22);
		panel_2.add(lblFactura);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFecha.setBounds(10, 33, 79, 22);
		panel_2.add(lblFecha);
		
		lblObservacion = new JLabel("Observaci\u00F3n");
		lblObservacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblObservacion.setBounds(234, 21, 95, 26);
		panel_2.add(lblObservacion);
		
		lblFechaValor = new JLabel("...");
		lblFechaValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaValor.setBounds(106, 33, 118, 22);
		panel_2.add(lblFechaValor);
		
		lblTipoFactura = new JLabel("Tipo Factura");
		lblTipoFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoFactura.setBounds(10, 58, 79, 26);
		panel_2.add(lblTipoFactura);
		
		comboBoxTipoFactura = new JComboBox();
		comboBoxTipoFactura.setBounds(106, 60, 118, 22);
		panel_2.add(comboBoxTipoFactura);
		
		lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumero.setBounds(10, 83, 79, 26);
		panel_2.add(lblNumero);
		
		lblNumeroFacturaValor = new JLabel("...");
		lblNumeroFacturaValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumeroFacturaValor.setBounds(106, 83, 118, 26);
		panel_2.add(lblNumeroFacturaValor);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(339, 16, 501, 93);
		panel_2.add(textArea);
		
		btnSeleccionarCaja = new JButton("Seleccionar caja");
		btnSeleccionarCaja.setBounds(732, 32, 128, 23);
		add(btnSeleccionarCaja);
		
		lblCajaValor = new JLabel("...");
		lblCajaValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCajaValor.setBounds(917, 577, 78, 23);
		add(lblCajaValor);
		
		lblCaja = new JLabel("Caja:");
		lblCaja.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCaja.setBounds(870, 576, 58, 23);
		add(lblCaja);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(1078, 577, 64, 18);
		add(lblUsuario);
		
		lblUsuarioValor = new JLabel("...");
		lblUsuarioValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuarioValor.setBounds(1152, 577, 108, 18);
		add(lblUsuarioValor);

	}
}
