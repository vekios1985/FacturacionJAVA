package org.facturacion.principal.vista.administracion;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


import org.facturacion.principal.models.Categoria;

import javax.swing.JScrollPane;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class FormAltaCategorias extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldCategoria;
	public JList<Categoria> list;
	public DefaultListModel<Categoria> modelo;
	public JLabel lblCategorias;
	public JButton btnGuardar;



	public FormAltaCategorias(JFrame frame,boolean modal) {
		super(frame,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 249, 434);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 70, 213, 315);
			getContentPane().add(scrollPane);
			
			modelo=new DefaultListModel<Categoria>();
		list = new JList<Categoria>(modelo);
		
			scrollPane.setViewportView(list);
		
	
		lblCategorias = new JLabel("Categorias");
		lblCategorias.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategorias.setBounds(10, 11, 99, 14);
		getContentPane().add(lblCategorias);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(10, 36, 114, 20);
		getContentPane().add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(134, 35, 89, 23);
		getContentPane().add(btnGuardar);
	}
}
