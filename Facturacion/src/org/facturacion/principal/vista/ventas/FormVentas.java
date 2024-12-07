package org.facturacion.principal.vista.ventas;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FormVentas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JDateChooser dateChooserFin;
	public JDateChooser dateChooserInicio;
	public JTable table;
	public JRadioButton rdbtnCaja;
	public JRadioButton rdbtnUsuario;
	public JRadioButton rdbtnTodos;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxCaja;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxUsuario;
	public JLabel lblDesde;
	public JLabel lblHasta;
	public JButton btnBuscar;
	public JButton btnPDf;
	public JButton btnExcel;
	

	
	@SuppressWarnings("rawtypes")
	public FormVentas(JFrame frame) {
		super(frame,true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 513);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		rdbtnCaja = new JRadioButton("Caja");
		rdbtnCaja.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGroup.add(rdbtnCaja);
		rdbtnCaja.setBounds(6, 10, 72, 23);
		getContentPane().add(rdbtnCaja);
		
		rdbtnUsuario = new JRadioButton("Usuario");
		rdbtnUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGroup.add(rdbtnUsuario);
		rdbtnUsuario.setBounds(6, 50, 72, 23);
		getContentPane().add(rdbtnUsuario);
		
		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonGroup.add(rdbtnTodos);
		rdbtnTodos.setBounds(6, 90, 72, 23);
		getContentPane().add(rdbtnTodos);
		
		comboBoxCaja = new JComboBox();
		comboBoxCaja.setBounds(84, 11, 126, 22);
		getContentPane().add(comboBoxCaja);
		
		comboBoxUsuario = new JComboBox();
		comboBoxUsuario.setBounds(84, 51, 126, 22);
		getContentPane().add(comboBoxUsuario);
		
		dateChooserFin = new JDateChooser();
		dateChooserFin.setDateFormatString("dd-MM-yyyy");
		dateChooserFin.setBounds(351, 50, 132, 23);
        getContentPane().add(dateChooserFin);
        
        dateChooserInicio = new JDateChooser();
        dateChooserInicio.setDateFormatString("dd-MM-yyyy");
        dateChooserInicio.setBounds(351, 10, 132, 23);
        getContentPane().add(dateChooserInicio);
        
        lblDesde = new JLabel("Desde");
        lblDesde.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDesde.setBounds(254, 14, 46, 14);
        getContentPane().add(lblDesde);
        
        lblHasta = new JLabel("Hasta");
        lblHasta.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblHasta.setBounds(254, 54, 46, 14);
        getContentPane().add(lblHasta);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(394, 91, 89, 23);
        getContentPane().add(btnBuscar);
        
        btnPDf = new JButton("PDF");
        btnPDf.setBounds(394, 440, 89, 23);
        getContentPane().add(btnPDf);
        
        btnExcel = new JButton("Excel");
        btnExcel.setBounds(295, 440, 89, 23);
        getContentPane().add(btnExcel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 120, 477, 307);
        getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
	}
}
