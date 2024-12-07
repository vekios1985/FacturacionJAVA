package org.facturacion.principal.vista.productos;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FormListarProductos extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldNombre;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JTable table;
	public JRadioButton rdbtnTodos;
	public JRadioButton rdbtnCategorias;
	public JRadioButton rdbtnNombre;
	public JRadioButton rdbtnProveedor;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxCategoria;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxProveedor;
	public JButton btnListar;
	public JButton btnExcel;
	public JButton btnPdf;

	

	
	@SuppressWarnings("rawtypes")
	public FormListarProductos(JFrame frame,boolean modal) {
		super(frame,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 781, 449);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			rdbtnTodos = new JRadioButton("Todos");
			buttonGroup.add(rdbtnTodos);
			rdbtnTodos.setBounds(6, 7, 109, 23);
			getContentPane().add(rdbtnTodos);
		}
		{
			rdbtnCategorias = new JRadioButton("Categoria");
			buttonGroup.add(rdbtnCategorias);
			rdbtnCategorias.setBounds(6, 75, 109, 23);
			getContentPane().add(rdbtnCategorias);
		}
		{
			rdbtnNombre = new JRadioButton("Nombre");
			buttonGroup.add(rdbtnNombre);
			rdbtnNombre.setBounds(289, 7, 109, 23);
			getContentPane().add(rdbtnNombre);
		}
		{
			rdbtnProveedor = new JRadioButton("Proveedor");
			buttonGroup.add(rdbtnProveedor);
			rdbtnProveedor.setBounds(289, 75, 109, 23);
			getContentPane().add(rdbtnProveedor);
		}
		{
			comboBoxCategoria = new JComboBox();
			comboBoxCategoria.setBounds(121, 75, 162, 22);
			getContentPane().add(comboBoxCategoria);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(404, 8, 168, 20);
			getContentPane().add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			comboBoxProveedor = new JComboBox();
			comboBoxProveedor.setBounds(404, 75, 168, 22);
			getContentPane().add(comboBoxProveedor);
		}
		{
			btnListar = new JButton("Listar");
			btnListar.setBounds(666, 7, 89, 23);
			getContentPane().add(btnListar);
		}
		{
			btnExcel = new JButton("Excel");
			btnExcel.setBounds(666, 41, 89, 23);
			getContentPane().add(btnExcel);
		}
		{
			btnPdf = new JButton("PDF");
			btnPdf.setBounds(666, 75, 89, 23);
			getContentPane().add(btnPdf);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 105, 749, 294);
			getContentPane().add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
	}

}
