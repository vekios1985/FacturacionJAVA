package org.facturacion.principal.vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

public class FormPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JMenu mnNewMenuInicio;
	public JMenuItem mntmSalir;
	public JMenu mnNewMenuClientes;
	public JMenu mnNewMenuProveedores;
	public JMenu mnNewMenuProductos;
	public JMenu mnNewMenuVentas;
	public JMenuItem mntmAltaClientes;
	public JMenu mnNewMenuAdministracion;
	public JMenuItem mntmAltaIva;
	public JMenuItem mntmEditarCliente;
	public JMenuItem mntmListarClientes;
	public JMenuItem mntmNuevoProveedor;
	public JMenuItem mntmEditarProveedor;
	public JMenuItem mntmListarProveedor;
	public JMenuItem mntmCategorias;
	public JMenuItem mntmAltaProducto;
	public JMenuItem mntmEditarProducto;
	public JMenuItem mntmListarProductos;
	public JMenuItem mntmIngresarStock;
	public FormFacturacion panel;
	public JMenuItem mntmTipoFactura;
	public JMenuItem mntmAltaCaja;
	public JMenuItem mntmUsuarios;
	public JMenuItem mntmListarFacturas;
	public JMenuItem mntmBuscarFactura;
	public JMenuItem mntmVentas;
	public JMenuItem mntmMiPerfil;
	public JMenuItem mntmCambiarUsuario;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public FormPrincipal() {
		/*try {
			
			
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
		}*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1300, 680);
		this.setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenuInicio = new JMenu("Inicio");
		menuBar.add(mnNewMenuInicio);
		
		mntmMiPerfil = new JMenuItem("Mi Perfil");
		mnNewMenuInicio.add(mntmMiPerfil);
		
		mntmCambiarUsuario = new JMenuItem("Cambiar Usuario");
		mnNewMenuInicio.add(mntmCambiarUsuario);
		
		mntmSalir = new JMenuItem("Salir");
		mnNewMenuInicio.add(mntmSalir);
		
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
		
		mntmAltaProducto = new JMenuItem("Cargar Producto");
		mnNewMenuProductos.add(mntmAltaProducto);
		
		mntmEditarProducto = new JMenuItem("Editar Producto");
		mnNewMenuProductos.add(mntmEditarProducto);
		
		mntmListarProductos = new JMenuItem("Listar Productos");
		mnNewMenuProductos.add(mntmListarProductos);
		
		mntmIngresarStock = new JMenuItem("Ingresar Mercaderia");
		mnNewMenuProductos.add(mntmIngresarStock);
		
		mnNewMenuVentas = new JMenu("Ventas");
		menuBar.add(mnNewMenuVentas);
		
		mntmListarFacturas = new JMenuItem("Listar Facturas");
		mnNewMenuVentas.add(mntmListarFacturas);
		
		mntmBuscarFactura = new JMenuItem("Buscar Factura");
		mnNewMenuVentas.add(mntmBuscarFactura);
		
		mntmVentas = new JMenuItem("Ventas");
		mnNewMenuVentas.add(mntmVentas);
		
		mnNewMenuAdministracion = new JMenu("Administracion");
		menuBar.add(mnNewMenuAdministracion);
		
		mntmAltaIva = new JMenuItem("Alta Item IVA");
		mnNewMenuAdministracion.add(mntmAltaIva);
		
		mntmCategorias = new JMenuItem("Categoria productos");
		mnNewMenuAdministracion.add(mntmCategorias);
		
		mntmTipoFactura = new JMenuItem("Nuevo Tipo Factura");
		mnNewMenuAdministracion.add(mntmTipoFactura);
		
		mntmAltaCaja = new JMenuItem("Nueva Caja");
		mnNewMenuAdministracion.add(mntmAltaCaja);
		
		mntmUsuarios = new JMenuItem("Usuarios");
		mnNewMenuAdministracion.add(mntmUsuarios);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new FormFacturacion();
		contentPane.add(panel, BorderLayout.CENTER);
	}
}
