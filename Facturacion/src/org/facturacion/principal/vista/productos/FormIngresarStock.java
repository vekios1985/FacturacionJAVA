package org.facturacion.principal.vista.productos;



import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


import org.facturacion.principal.utils.AutocompleteComboBox;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;


public class FormIngresarStock extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldStock;
	public JTextField textFieldPrecio;
	public JLabel lblIngresoStock;
	public JLabel lblProducto;
	public JLabel lblProveedor;
	public JLabel lblStock;
	public JLabel lblPrecio;
	public AutocompleteComboBox comboBoxProducto;
	public AutocompleteComboBox comboBoxProveedor;
	public JLabel lblInfo;
	public JButton btnAgregar;
	@SuppressWarnings("rawtypes")
	public DefaultComboBoxModel modelo=new DefaultComboBoxModel();
	
	

	
	public FormIngresarStock(JFrame frame,boolean modal) {
		super(frame,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 319, 365);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			lblIngresoStock = new JLabel("Ingresar Mercaderia");
			lblIngresoStock.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblIngresoStock.setBounds(10, 11, 179, 25);
			getContentPane().add(lblIngresoStock);
		}
		{
			lblProducto = new JLabel("Producto");
			lblProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProducto.setBounds(10, 50, 94, 14);
			getContentPane().add(lblProducto);
		}
		{
			lblProveedor = new JLabel("Proveedor");
			lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblProveedor.setBounds(10, 90, 94, 14);
			getContentPane().add(lblProveedor);
		}
		{
			lblStock = new JLabel("Unidades a ingresar");
			lblStock.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblStock.setBounds(10, 130, 120, 14);
			getContentPane().add(lblStock);
		}
		{
			lblPrecio = new JLabel("Precio");
			lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblPrecio.setBounds(10, 170, 94, 14);
			getContentPane().add(lblPrecio);
		}
		
		{
			comboBoxProducto = new AutocompleteComboBox(modelo);
			comboBoxProducto.setBounds(140, 47, 145, 25);
			getContentPane().add(comboBoxProducto);
		}
		{
			comboBoxProveedor = new AutocompleteComboBox(modelo);
			comboBoxProveedor.setBounds(140, 87, 145, 25);
			getContentPane().add(comboBoxProveedor);
		}{
			textFieldStock = new JTextField();
			textFieldStock.setBounds(140, 128, 145, 20);
			getContentPane().add(textFieldStock);
			textFieldStock.setColumns(10);
		}
		{
			textFieldPrecio = new JTextField();
			textFieldPrecio.setBounds(140, 168, 145, 20);
			getContentPane().add(textFieldPrecio);
			textFieldPrecio.setColumns(10);
		}
		{
			lblInfo = new JLabel("...");
			lblInfo.setBounds(10, 210, 275, 71);
			getContentPane().add(lblInfo);
		}
		{
			btnAgregar = new JButton("Agregar");
			btnAgregar.setBounds(196, 292, 89, 23);
			getContentPane().add(btnAgregar);
		}
	}

}
