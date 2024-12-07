package org.facturacion.principal.vista.administracion;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FormUsuarios extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldDni;
	public JTextField textFieldNombre;
	public JTextField textFieldApellido;
	public JTextField textFieldUsername;
	public JPasswordField passwordField;
	public DefaultTableModel modeloTabla;
	public JTable table;
	public JLabel lblDni;
	public JLabel lblNombre;
	public JLabel lblApellido;
	public JLabel lbluserName;
	public JLabel lblPassword;
	public JLabel lblRole;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxRole;
	public JButton btnGuardar;
	public JButton btnEditar;
	
	
	
	
	@SuppressWarnings("rawtypes")
	public FormUsuarios(JFrame frame,boolean modal) {
		super(frame,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 436);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			lblDni = new JLabel("DNI");
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDni.setBounds(10, 11, 66, 19);
			getContentPane().add(lblDni);
		}
		{
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNombre.setBounds(10, 41, 66, 19);
			getContentPane().add(lblNombre);
		}
		{
			lblApellido = new JLabel("Apellido");
			lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblApellido.setBounds(10, 71, 66, 24);
			getContentPane().add(lblApellido);
		}
		{
			lbluserName = new JLabel("Username");
			lbluserName.setFont(new Font("Tahoma", Font.BOLD, 12));
			lbluserName.setBounds(246, 11, 66, 19);
			getContentPane().add(lbluserName);
		}
		{
			lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPassword.setBounds(246, 41, 66, 19);
			getContentPane().add(lblPassword);
		}
		{
			lblRole = new JLabel("Role");
			lblRole.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblRole.setBounds(246, 71, 66, 24);
			getContentPane().add(lblRole);
		}
		{
			textFieldDni = new JTextField();
			textFieldDni.setBounds(66, 11, 156, 20);
			getContentPane().add(textFieldDni);
			textFieldDni.setColumns(10);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(66, 41, 156, 20);
			getContentPane().add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			textFieldApellido = new JTextField();
			textFieldApellido.setBounds(66, 74, 156, 20);
			getContentPane().add(textFieldApellido);
			textFieldApellido.setColumns(10);
		}
		{
			textFieldUsername = new JTextField();
			textFieldUsername.setBounds(322, 11, 156, 20);
			getContentPane().add(textFieldUsername);
			textFieldUsername.setColumns(10);
		}
		
		passwordField = new JPasswordField();
		passwordField.setBounds(322, 41, 156, 20);
		getContentPane().add(passwordField);
		
		comboBoxRole = new JComboBox();
		comboBoxRole.setBounds(322, 73, 156, 22);
		getContentPane().add(comboBoxRole);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(524, 40, 89, 23);
		getContentPane().add(btnGuardar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(524, 73, 89, 23);
		getContentPane().add(btnEditar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 603, 280);
		getContentPane().add(scrollPane);
		
		modeloTabla=new DefaultTableModel();
		
		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);
	}
}
