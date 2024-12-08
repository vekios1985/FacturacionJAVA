package org.facturacion.principal.vista;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

public class FormMiPerfil extends JDialog {

	private static final long serialVersionUID = 1L;
	public JLabel lblUsername;
	public JLabel lblNombre;
	public JLabel lblApellido;
	public JLabel lblDni;
	public JLabel lblRole;
	public JLabel lblUsuarioValor;
	public JLabel lblNombreValor;
	public JLabel lblApellidoValor;
	public JLabel lblDniValor;
	public JLabel lblRoleValor;
	public JButton btnSetPassword;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBox;
	public JLabel lblAspecto;
	

	
	@SuppressWarnings("rawtypes")
	public FormMiPerfil(JFrame frame) {
		super(frame,true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 269, 305);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		lblUsername = new JLabel("Usuario:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(10, 10, 68, 14);
		getContentPane().add(lblUsername);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(10, 45, 68, 14);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellido.setBounds(10, 80, 68, 14);
		getContentPane().add(lblApellido);
		
		 lblDni = new JLabel("DNI:");
		 lblDni.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDni.setBounds(10, 115, 68, 14);
		getContentPane().add(lblDni);
		
		lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRole.setBounds(10, 150, 68, 14);
		getContentPane().add(lblRole);
		
		lblUsuarioValor = new JLabel("...");
		lblUsuarioValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuarioValor.setBounds(100, 10, 156, 14);
		getContentPane().add(lblUsuarioValor);
		
		lblNombreValor = new JLabel("...");
		lblNombreValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombreValor.setBounds(100, 45, 156, 14);
		getContentPane().add(lblNombreValor);
		
		lblApellidoValor = new JLabel("...");
		lblApellidoValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellidoValor.setBounds(100, 80, 156, 14);
		getContentPane().add(lblApellidoValor);
		
		lblDniValor = new JLabel("...");
		lblDniValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDniValor.setBounds(100, 115, 156, 14);
		getContentPane().add(lblDniValor);
		
		lblRoleValor = new JLabel("...");
		lblRoleValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRoleValor.setBounds(100, 150, 156, 14);
		getContentPane().add(lblRoleValor);
		
		btnSetPassword = new JButton("Cambiar Contraseña");
		btnSetPassword.setBounds(40, 232, 174, 23);
		getContentPane().add(btnSetPassword);
		
		lblAspecto = new JLabel("Aspecto");
		lblAspecto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAspecto.setBounds(10, 194, 68, 14);
		getContentPane().add(lblAspecto);
		
		comboBox = new JComboBox();
		comboBox.setBounds(100, 190, 114, 22);
		getContentPane().add(comboBox);
	}
}
