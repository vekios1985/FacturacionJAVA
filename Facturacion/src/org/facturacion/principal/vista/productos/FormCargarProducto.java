package org.facturacion.principal.vista.productos;



import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.utils.AutocompleteComboBox;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class FormCargarProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldNombre;
	public JTextField textFieldCodigo;
	public JLabel lblNuevoProducto;
	public JLabel lblNombre;
	public JLabel lblCodigo;
	public JLabel lblCategoria;
	public AutocompleteComboBox comboBoxCategoria;
	public DefaultComboBoxModel<Categoria>modelo;
	public JButton btnAgregar;
	public JButton btnBuscar;

	
	public FormCargarProducto(JFrame frame,boolean modal) {
		super(frame,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 291, 260);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			lblNuevoProducto = new JLabel("Cargar producto nuevo");
			lblNuevoProducto.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNuevoProducto.setBounds(10, 11, 207, 14);
			getContentPane().add(lblNuevoProducto);
		}
		{
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNombre.setBounds(10, 60, 111, 14);
			getContentPane().add(lblNombre);
		}
		{
			lblCodigo = new JLabel("Codigo");
			lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCodigo.setBounds(10, 100, 111, 14);
			getContentPane().add(lblCodigo);
		}
		{
			lblCategoria = new JLabel("Categoria");
			lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCategoria.setBounds(10, 140, 111, 14);
			getContentPane().add(lblCategoria);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(131, 57, 130, 20);
			getContentPane().add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			textFieldCodigo = new JTextField();
			textFieldCodigo.setBounds(131, 97, 130, 20);
			getContentPane().add(textFieldCodigo);
			textFieldCodigo.setColumns(10);
		}
		modelo=new DefaultComboBoxModel<Categoria>();
		comboBoxCategoria = new AutocompleteComboBox(modelo);
		comboBoxCategoria.setBounds(131, 136, 130, 22);
		getContentPane().add(comboBoxCategoria);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(176, 180, 89, 23);
		getContentPane().add(btnAgregar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 180, 89, 23);
		getContentPane().add(btnBuscar);
	}
}
