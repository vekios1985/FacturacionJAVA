package org.facturacion.principal.vista.clientes;



import javax.swing.JButton;
import javax.swing.JDialog;


import org.facturacion.principal.models.Iva;
import org.facturacion.principal.vista.FormPrincipal;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;

public class FormAltaCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public JTextField textFieldNombre;
	public JTextField textFieldApellido;
	public JTextField textFieldTelefono;
	public JTextField textFieldDireccion;
	public JTextField textFieldLocalidad;
	public JTextField textFieldEmail;
	public JTextField textFieldDni;
	public JLabel lblDni;
	public JLabel lblNombre;
	public JLabel lblApellido;
	public JLabel lblTelefono;
	public JLabel lblDireccion;
	public JLabel lblLocalidad;
	public JLabel lblEmail;
	public JLabel lblTipoIva;
	public JComboBox<Iva> comboBox;
	public JButton btnGuardar;
	public JLabel lblTexto;
	public JButton btnBuscar;

	
	public FormAltaCliente(FormPrincipal principal,boolean modal) {
		super(principal,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 348, 367);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDni.setBounds(10, 53, 115, 14);
		getContentPane().add(lblDni);
		
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNombre.setBounds(10, 83, 115, 14);
			getContentPane().add(lblNombre);
		
		
			lblApellido = new JLabel("Apellido");
			lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblApellido.setBounds(10, 113, 115, 14);
			getContentPane().add(lblApellido);
		
		
			lblTelefono = new JLabel("Telefono");
			lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTelefono.setBounds(10, 143, 115, 14);
			getContentPane().add(lblTelefono);
		
		
			lblDireccion = new JLabel("Direcci\u00F3n");
			lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDireccion.setBounds(10, 173, 115, 14);
			getContentPane().add(lblDireccion);
		
		
			lblLocalidad = new JLabel("Localidad");
			lblLocalidad.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblLocalidad.setBounds(10, 203, 115, 14);
			getContentPane().add(lblLocalidad);
		
		
			lblEmail = new JLabel("eMail");
			lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblEmail.setBounds(10, 233, 115, 14);
			getContentPane().add(lblEmail);
		
		
			lblTipoIva = new JLabel("Tipo IVA");
			lblTipoIva.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTipoIva.setBounds(10, 263, 115, 14);
			getContentPane().add(lblTipoIva);
		
			textFieldDni = new JTextField();
			textFieldDni.setBounds(150, 51, 160, 20);
			getContentPane().add(textFieldDni);
			textFieldDni.setColumns(10);
			
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(150, 80, 160, 20);
			getContentPane().add(textFieldNombre);
			textFieldNombre.setColumns(10);
		
		
			textFieldApellido = new JTextField();
			textFieldApellido.setBounds(150, 110, 160, 20);
			getContentPane().add(textFieldApellido);
			textFieldApellido.setColumns(10);
		
		
			textFieldTelefono = new JTextField();
			textFieldTelefono.setBounds(150, 140, 160, 20);
			getContentPane().add(textFieldTelefono);
			textFieldTelefono.setColumns(10);
		
		
			textFieldDireccion = new JTextField();
			textFieldDireccion.setBounds(150, 170, 160, 20);
			getContentPane().add(textFieldDireccion);
			textFieldDireccion.setColumns(10);
		
		
			textFieldLocalidad = new JTextField();
			textFieldLocalidad.setBounds(150, 200, 160, 20);
			getContentPane().add(textFieldLocalidad);
			textFieldLocalidad.setColumns(10);
		
		
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(150, 230, 160, 20);
			getContentPane().add(textFieldEmail);
			textFieldEmail.setColumns(10);
		
		
			comboBox = new JComboBox<Iva>();
			comboBox.setBounds(150, 259, 160, 22);
			getContentPane().add(comboBox);
			
			btnGuardar = new JButton("Guardar");
			btnGuardar.setBounds(221, 292, 89, 23);
			getContentPane().add(btnGuardar);
			
			lblTexto = new JLabel("Alta Nuevo Cliente");
			lblTexto.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTexto.setBounds(86, 11, 141, 14);
			getContentPane().add(lblTexto);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(10, 292, 89, 23);
			getContentPane().add(btnBuscar);
			
			
			
		
	}
}
