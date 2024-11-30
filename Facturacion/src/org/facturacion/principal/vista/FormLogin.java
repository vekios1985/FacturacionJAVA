package org.facturacion.principal.vista;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class FormLogin extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public JButton btnSalir;
	public JButton btnIngresar;
	public JTextField textFieldUsuario;
	public JPasswordField passwordFieldPassword;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public FormLogin(FormPrincipal principal,boolean modal) {
		super(principal, modal);
		setUndecorated(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setBounds(100, 100, 250, 400); // Ajusta el tamaño inicial de la ventana
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// Escalar la imagen
		ImageIcon originalIcon = new ImageIcon(FormLogin.class.getResource("/images/fondoBlue.jpg"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(250,400, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		contentPanel.setLayout(null);
		
		btnIngresar = new JButton("Validar");
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIngresar.setBackground(new Color(0, 0, 160));
		btnIngresar.setBounds(136, 350, 89, 23);
		contentPanel.add(btnIngresar);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setForeground(new Color(255, 255, 255));
		passwordFieldPassword.setBackground(new Color(0, 0, 128));
		passwordFieldPassword.setBounds(96, 319, 129, 20);
		contentPanel.add(passwordFieldPassword);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBackground(new Color(0, 0, 128));
		textFieldUsuario.setForeground(new Color(255, 255, 255));
		textFieldUsuario.setBounds(96, 276, 129, 20);
		contentPanel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 322, 76, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsuario.setBounds(10, 279, 76, 14);
		contentPanel.add(lblUsuario);
		
		btnSalir = new JButton("Salir");
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.setBackground(new Color(0, 0, 160));
		btnSalir.setBounds(154, 11, 71, 23);
		contentPanel.add(btnSalir);
		
		JLabel lblIcono = new JLabel("");
		
		ImageIcon originalIcon2 = new ImageIcon(FormLogin.class.getResource("/images/user2.png"));
		Image scaledImage2 = originalIcon2.getImage().getScaledInstance(156,210, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
		lblIcono.setIcon(scaledIcon2);
		lblIcono.setBounds(45, 45, 156, 206);
		contentPanel.add(lblIcono);

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 250, 400);
		lblFondo.setIcon(scaledIcon);
		contentPanel.add(lblFondo);
	}
}
