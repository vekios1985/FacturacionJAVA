package org.facturacion.principal.vista.ventas;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Font;

public class FormFacturaDetalle extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField textField;
	public JTable table;
	public JLabel lblTipoFactura;
	@SuppressWarnings("rawtypes")
	public JComboBox comboBox;
	public JLabel lblNumero;
	public JButton btnBuscar;
	public JLabel lblFecha;
	public JLabel lblFechaValor;
	public JLabel lblCliente;
	public JLabel lblClienteValor;
	public JLabel lblDescuento;
	public JLabel lblDescuentoValor;
	public JLabel lblTotal ;
	public JLabel lblTotalValor;
	public JLabel lblObservacionValor;
	public JButton btnPdf;
	

	
	@SuppressWarnings("rawtypes")
	public FormFacturaDetalle(JFrame frame) {
		super(frame,true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 890, 625);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			lblTipoFactura = new JLabel("Tipo Factura");
			lblTipoFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTipoFactura.setBounds(10, 11, 75, 23);
			getContentPane().add(lblTipoFactura);
		}
		{
			comboBox = new JComboBox();
			comboBox.setBounds(95, 11, 101, 22);
			getContentPane().add(comboBox);
		}
		{
			lblNumero = new JLabel("Numero");
			lblNumero.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNumero.setBounds(242, 15, 75, 19);
			getContentPane().add(lblNumero);
		}
		{
			textField = new JTextField();
			textField.setBounds(327, 12, 119, 23);
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		{
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(549, 11, 89, 23);
			getContentPane().add(btnBuscar);
		}
		{
			lblFecha = new JLabel("Fecha:");
			lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblFecha.setBounds(10, 59, 46, 14);
			getContentPane().add(lblFecha);
		}
		{
			lblFechaValor = new JLabel("...");
			lblFechaValor.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblFechaValor.setBounds(95, 59, 101, 14);
			getContentPane().add(lblFechaValor);
		}
		{
			lblCliente = new JLabel("Cliente:");
			lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblCliente.setBounds(242, 59, 46, 14);
			getContentPane().add(lblCliente);
		}
		{
			lblClienteValor = new JLabel("...");
			lblClienteValor.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblClienteValor.setBounds(327, 59, 223, 14);
			getContentPane().add(lblClienteValor);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 84, 850, 343);
			getContentPane().add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			lblDescuento = new JLabel("Descuento:");
			lblDescuento.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDescuento.setBounds(10, 561, 75, 14);
			getContentPane().add(lblDescuento);
		}
		{
			lblDescuentoValor = new JLabel("...");
			lblDescuentoValor.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDescuentoValor.setBounds(95, 561, 101, 14);
			getContentPane().add(lblDescuentoValor);
		}
		{
			lblTotal = new JLabel("Total:");
			lblTotal.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblTotal.setBounds(327, 552, 75, 23);
			getContentPane().add(lblTotal);
		}
		{
			lblTotalValor = new JLabel("...");
			lblTotalValor.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblTotalValor.setBounds(446, 552, 165, 23);
			getContentPane().add(lblTotalValor);
		}
		{
			lblObservacionValor = new JLabel("...");
			lblObservacionValor.setHorizontalAlignment(SwingConstants.LEFT);
			lblObservacionValor.setBounds(10, 442, 850, 108);
			getContentPane().add(lblObservacionValor);
		}
		{
			btnPdf = new JButton("PDF");
			btnPdf.setBounds(771, 11, 89, 62);
			getContentPane().add(btnPdf);
		}
	}

}
