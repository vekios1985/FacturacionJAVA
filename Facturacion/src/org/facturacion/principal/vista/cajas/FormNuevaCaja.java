package org.facturacion.principal.vista.cajas;



import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class FormNuevaCaja extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textFieldNombreCaja;
	public JLabel lblNombreCaja;
	public JButton btnAgregar;
	@SuppressWarnings("rawtypes")
	public JList list;

	
	@SuppressWarnings("rawtypes")
	public FormNuevaCaja(JFrame frame,boolean modal) {
		super(frame,modal);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 292, 418);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombreCaja = new JLabel("Nombre Caja");
		lblNombreCaja.setBounds(10, 11, 111, 23);
		contentPane.add(lblNombreCaja);
		
		textFieldNombreCaja = new JTextField();
		textFieldNombreCaja.setBounds(108, 12, 147, 20);
		contentPane.add(textFieldNombreCaja);
		textFieldNombreCaja.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(166, 54, 89, 23);
		contentPane.add(btnAgregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 256, 273);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
	}
}
