package org.facturacion.principal.vista.administracion;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class FormAltaIva extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textFieldIva;
	public JButton btnGuardar;
	public JLabel lblNewLabelTitulo;

	
	public FormAltaIva(JFrame principal,boolean modal) {
		super(principal,modal);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 205, 173);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
			lblNewLabelTitulo = new JLabel("Nuevo IVA");
			lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabelTitulo.setBounds(10, 11, 160, 14);
			getContentPane().add(lblNewLabelTitulo);
		
		
			textFieldIva = new JTextField();
			textFieldIva.setBounds(10, 36, 160, 20);
			getContentPane().add(textFieldIva);
			textFieldIva.setColumns(10);
		
		
			 btnGuardar = new JButton("Guardar");
			btnGuardar.setBounds(81, 82, 89, 23);
			getContentPane().add(btnGuardar);
		
	}

}
