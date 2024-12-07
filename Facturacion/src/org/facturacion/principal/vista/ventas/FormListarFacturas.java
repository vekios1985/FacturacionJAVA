package org.facturacion.principal.vista.ventas;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


import com.toedter.calendar.JDateChooser;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FormListarFacturas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JRadioButton rdbtnTodas;
	public JRadioButton rdbtnCliente;
	public JRadioButton rdbtnTipoFactura;
	public JRadioButton rdbtnFecha;
	public JTextField textField;
	public JDateChooser dateChooser;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBox;
	
	public JButton btnExcel;
	public JButton btnPdf;
	public JButton btnListar;
	public JTable table;

	
	@SuppressWarnings("rawtypes")
	public FormListarFacturas(JFrame frame) {
		super(frame,true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 697, 430);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		rdbtnTodas = new JRadioButton("Todas");
		rdbtnTodas.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGroup.add(rdbtnTodas);
		rdbtnTodas.setBounds(280, 52, 109, 23);
		getContentPane().add(rdbtnTodas);
		
		rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGroup.add(rdbtnCliente);
		rdbtnCliente.setBounds(280, 7, 73, 23);
		getContentPane().add(rdbtnCliente);
		
		rdbtnTipoFactura = new JRadioButton("Tipo Factura");
		rdbtnTipoFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGroup.add(rdbtnTipoFactura);
		rdbtnTipoFactura.setBounds(6, 52, 101, 23);
		getContentPane().add(rdbtnTipoFactura);
		
		rdbtnFecha = new JRadioButton("Fecha");
		rdbtnFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGroup.add(rdbtnFecha);
		rdbtnFecha.setBounds(6, 7, 109, 23);
		getContentPane().add(rdbtnFecha);
	
        
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setBounds(114, 7, 132, 23);
        getContentPane().add(dateChooser);
        
        comboBox = new JComboBox();
        comboBox.setBounds(114, 53, 132, 22);
        getContentPane().add(comboBox);
        
     
       
        
        textField = new JTextField();
        textField.setBounds(357, 9, 132, 20);
        getContentPane().add(textField);
        textField.setColumns(10);
        
        
        btnListar = new JButton("Listar");
        btnListar.setBounds(400, 53, 89, 23);
        getContentPane().add(btnListar);
        
        btnExcel = new JButton("Excel");
        btnExcel.setBounds(582, 8, 89, 23);
        getContentPane().add(btnExcel);
        
        btnPdf = new JButton("PDF");
        btnPdf.setBounds(582, 53, 89, 23);
        getContentPane().add(btnPdf);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 93, 665, 287);
        getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);

	}
}
