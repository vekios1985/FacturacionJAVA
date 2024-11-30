package org.facturacion.principal.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FormPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JMenu mnNewMenuInicio;
	public JMenuItem mntmNewMenuItem;
	public JMenu mnNewMenuClientes;
	public JMenu mnNewMenuProveedores;
	public JMenu mnNewMenuProductos;
	public JMenu mnNewMenuVentas;
	public JMenu mnNewMenuCajas;
	public JMenuItem mntmAltaClientes;
	public JMenu mnNewMenuAdministracion;
	public JMenuItem mntmAltaIva;
	public JMenuItem mntmEditarCliente;
	public JMenuItem mntmListarClientes;
	public JMenuItem mntmNuevoProveedor;
	public JMenuItem mntmEditarProveedor;
	public JMenuItem mntmListarProveedor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormPrincipal() {
		try {
			
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//SwingUtilities.updateComponentTreeUI(FormPrincipal.this);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 900, 600);
		this.setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenuInicio = new JMenu("Inicio");
		menuBar.add(mnNewMenuInicio);
		
		mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenuInicio.add(mntmNewMenuItem);
		
		mnNewMenuClientes = new JMenu("Clientes");
		menuBar.add(mnNewMenuClientes);
		
		mntmAltaClientes = new JMenuItem("Nuevo cliente...");
		mnNewMenuClientes.add(mntmAltaClientes);
		
		mntmEditarCliente = new JMenuItem("Editar Cliente");
		mnNewMenuClientes.add(mntmEditarCliente);
		
		mntmListarClientes = new JMenuItem("Listar Clientes");
		mnNewMenuClientes.add(mntmListarClientes);
		
		mnNewMenuProveedores = new JMenu("Proveedores");
		menuBar.add(mnNewMenuProveedores);
		
		mntmNuevoProveedor = new JMenuItem("Nuevo Proveedor");
		mnNewMenuProveedores.add(mntmNuevoProveedor);
		
		mntmEditarProveedor = new JMenuItem("Editar Proveedor");
		mnNewMenuProveedores.add(mntmEditarProveedor);
		
		mntmListarProveedor = new JMenuItem("Listar Proveedores");
		mnNewMenuProveedores.add(mntmListarProveedor);
		
		mnNewMenuProductos = new JMenu("Productos");
		menuBar.add(mnNewMenuProductos);
		
		mnNewMenuVentas = new JMenu("Ventas");
		menuBar.add(mnNewMenuVentas);
		
		mnNewMenuCajas = new JMenu("Cajas");
		menuBar.add(mnNewMenuCajas);
		
		mnNewMenuAdministracion = new JMenu("Administracion");
		menuBar.add(mnNewMenuAdministracion);
		
		mntmAltaIva = new JMenuItem("Alta Item IVA");
		mnNewMenuAdministracion.add(mntmAltaIva);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

}
