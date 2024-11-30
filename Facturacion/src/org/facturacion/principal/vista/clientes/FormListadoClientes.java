package org.facturacion.principal.vista.clientes;

import java.awt.BorderLayout;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FormListadoClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldBuscar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JTable tableClientes;
	public JRadioButton rdbtnApellido;
	public JRadioButton rdbtnDni;
	public JButton btnBuscar;
	public JLabel lblBuscar;
	public DefaultTableModel tableModel;
	public JButton btnExcel;
	public JButton btnPdf;
	public JRadioButton rdbtnTodos;

	
	public FormListadoClientes(JFrame ventana,boolean modal) {
		super(ventana,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 426);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		lblBuscar = new JLabel("Buscar Clientes por...");
		panel.add(lblBuscar);
		
		
		
		rdbtnApellido = new JRadioButton("Apellido");
		buttonGroup.add(rdbtnApellido);
		panel.add(rdbtnApellido);
		
		rdbtnDni = new JRadioButton("DNI");
		buttonGroup.add(rdbtnDni);
		panel.add(rdbtnDni);
		
		rdbtnTodos = new JRadioButton("Todos");
		buttonGroup.add(rdbtnTodos);
		panel.add(rdbtnTodos);
		
		textFieldBuscar = new JTextField();
		panel.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		panel.add(btnBuscar);
		
		btnExcel = new JButton("XLS");
		panel.add(btnExcel);
		
		btnPdf = new JButton("PDF");
		panel.add(btnPdf);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 632, 332);
		panel_1.add(scrollPane);
		
		tableModel=new DefaultTableModel();
		
		tableClientes = new JTable(tableModel);
		scrollPane.setViewportView(tableClientes);
		
	}
}
