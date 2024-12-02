package org.facturacion.principal.vista.proveedores;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class FormAgregarProveedor extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldCuit;
	public JTextField textFieldNombre;
	public JTextField textFieldTelefono;
	public JTextField textFieldEmail;
	public JTextField textFieldDireccion;
	public JButton btnGuardar;
	public JLabel lblTitulo;
	public JLabel lblCuit;
	public JLabel lblNombre;
	public JLabel lblTelefono;
	public JLabel lblDireccion;
	public JLabel lblEmail;
	public JButton btnBuscar;

	
	public FormAgregarProveedor(JFrame frame,boolean modal) {
		super(frame,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 330);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Alta Proveedor");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(156, 11, 217, 24);
		getContentPane().add(lblTitulo);
		
		lblCuit = new JLabel("CUIT");
		lblCuit.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCuit.setBounds(10, 50, 89, 14);
		getContentPane().add(lblCuit);
		
		lblNombre = new JLabel("Razon Social");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(10, 90, 89, 14);
		getContentPane().add(lblNombre);
		
		lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefono.setBounds(10, 130, 89, 14);
		getContentPane().add(lblTelefono);
		
		lblDireccion = new JLabel("eMail");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDireccion.setBounds(10, 170, 89, 14);
		getContentPane().add(lblDireccion);
		
		lblEmail = new JLabel("Direcci\u00F3n");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 210, 89, 14);
		getContentPane().add(lblEmail);
		
		textFieldCuit = new JTextField();
		textFieldCuit.setBounds(102, 46, 336, 20);
		getContentPane().add(textFieldCuit);
		textFieldCuit.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(102, 87, 336, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(102, 127, 336, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(102, 167, 336, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(102, 207, 336, 20);
		getContentPane().add(textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setBounds(349, 252, 89, 23);
		getContentPane().add(btnGuardar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBounds(349, 14, 89, 23);
		getContentPane().add(btnBuscar);
	}
}
