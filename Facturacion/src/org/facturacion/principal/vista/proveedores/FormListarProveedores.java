package org.facturacion.principal.vista.proveedores;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.Font;

public class FormListarProveedores extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTable table;
	public JTextField textFieldBuscar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public DefaultTableModel modelo;
	public JRadioButton rdbtnTodos;
	public JRadioButton rdbtnNombre;
	public JButton btnListar;
	public JButton btnExcel;
	public JButton btnPdf;
	public JLabel lblBuscarProveedor;
	public JRadioButton rdbtncuit;
	
	
	public FormListarProveedores(JFrame principal,boolean modal) {
		super(principal,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 664, 367);
		getContentPane().add(scrollPane);
		modelo=new DefaultTableModel();
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnTodos.setSelected(true);
		buttonGroup.add(rdbtnTodos);
		rdbtnTodos.setBounds(164, 7, 109, 23);
		getContentPane().add(rdbtnTodos);
		
		rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdbtnNombre);
		rdbtnNombre.setBounds(164, 33, 109, 23);
		getContentPane().add(rdbtnNombre);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(269, 52, 168, 20);
		getContentPane().add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		btnListar = new JButton("Listar");
		btnListar.setBounds(269, 7, 89, 23);
		getContentPane().add(btnListar);
		
		btnExcel = new JButton("Excel");
		btnExcel.setBounds(585, 7, 89, 23);
		getContentPane().add(btnExcel);
		
		btnPdf = new JButton("Pdf");
		btnPdf.setBounds(585, 49, 89, 23);
		getContentPane().add(btnPdf);
		
		lblBuscarProveedor = new JLabel("Listar Proveedor");
		lblBuscarProveedor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBuscarProveedor.setBounds(10, 21, 137, 23);
		getContentPane().add(lblBuscarProveedor);
		
		rdbtncuit = new JRadioButton("CUIT");
		rdbtncuit.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonGroup.add(rdbtncuit);
		rdbtncuit.setBounds(164, 59, 109, 23);
		getContentPane().add(rdbtncuit);
	}
}
