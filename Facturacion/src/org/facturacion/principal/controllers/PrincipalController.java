package org.facturacion.principal.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.facturacion.principal.controllers.cajas.AltaCajasController;
import org.facturacion.principal.controllers.clientes.AltaClienteController;
import org.facturacion.principal.controllers.clientes.AltaIvaController;
import org.facturacion.principal.controllers.clientes.ListarClientesController;
import org.facturacion.principal.controllers.productos.CargarProductoController;
import org.facturacion.principal.controllers.productos.CategoriasController;
import org.facturacion.principal.controllers.productos.ListarProductosController;
import org.facturacion.principal.controllers.productos.StockController;
import org.facturacion.principal.controllers.proveedores.AltaProveedoresController;
import org.facturacion.principal.controllers.proveedores.ListarProveedoresController;
import org.facturacion.principal.models.Role;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.services.ILoginService;
import org.facturacion.principal.services.LoginService;
import org.facturacion.principal.vista.FormPrincipal;

@SuppressWarnings("unused")
public class PrincipalController {

	public FormPrincipal formPrincipal;
	
	

	private AltaClienteController altaClienteController;
	private AltaProveedoresController altaProveedoresController;
	private AltaIvaController altaIva;
	private ListarProveedoresController listarProveedoresController;
	private ListarClientesController listarClientesController;
	private LoginController controllerLogin;
	private CategoriasController categoriasController;
	private CargarProductoController cargarProductoController;
	private StockController stockController;
	private ListarProductosController listarProductosController;
	private FacturacionController facturacion;
	private AltaTipoFacturaController tipoFacturaController;
	private AltaCajasController  altaCajaController;
	private NuevoUsuarioController usuarioNuevoController;
	public Usuario usuario = null;

	private ILoginService service;

	public PrincipalController(FormPrincipal principal) {
		
		formPrincipal = principal;
		formPrincipal.setVisible(true);
		service = new LoginService();
		//usuario=service.
		deshabilitar();
		
		iniciarController();

	}

	void iniciarController() {
		
		controllerLogin = new LoginController(formPrincipal, true);
		
		usuario = controllerLogin.usuario;
		if (usuario != null) {
			habilitar(usuario);
			System.out.println("habilitado");
		} else {
			deshabilitar();
			System.out.println("deshabilitado");
		}
		
		facturacion=new FacturacionController(formPrincipal,usuario);
		setActionListener();

	}

	public void habilitar(Usuario usuario) {
		// Falta agregar la habilitacion de roles

		try {
			//Role role = service.obtenerRole(usuario);
			this.formPrincipal.mnNewMenuClientes.setEnabled(true);
			this.formPrincipal.mnNewMenuProveedores.setEnabled(true);
			this.formPrincipal.mnNewMenuProductos.setEnabled(true);
			this.formPrincipal.mnNewMenuVentas.setEnabled(true);
			this.formPrincipal.mnNewMenuCajas.setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(formPrincipal, e.getMessage(), "Error", 0);
		}

	}

	public void deshabilitar() {
		this.formPrincipal.mnNewMenuClientes.setEnabled(false);
		this.formPrincipal.mnNewMenuProveedores.setEnabled(false);
		this.formPrincipal.mnNewMenuProductos.setEnabled(false);
		this.formPrincipal.mnNewMenuVentas.setEnabled(false);
		this.formPrincipal.mnNewMenuCajas.setEnabled(false);
	}
	
	void setActionListener()
	{
		formPrincipal.mntmAltaClientes.addActionListener(accionesMenu);
		formPrincipal.mntmAltaIva.addActionListener(accionesMenu);
		formPrincipal.mntmEditarCliente.addActionListener(accionesMenu);
		formPrincipal.mntmListarClientes.addActionListener(accionesMenu);
		formPrincipal.mntmEditarProveedor.addActionListener(accionesMenu);
		formPrincipal.mntmListarProveedor.addActionListener(accionesMenu);
		formPrincipal.mntmNuevoProveedor.addActionListener(accionesMenu);
		formPrincipal.mntmCategorias.addActionListener(accionesMenu);
		formPrincipal.mntmAltaProducto.addActionListener(accionesMenu);
		formPrincipal.mntmEditarProducto.addActionListener(accionesMenu);
		formPrincipal.mntmListarProductos.addActionListener(accionesMenu);
		formPrincipal.mntmIngresarStock.addActionListener(accionesMenu);
		formPrincipal.mntmTipoFactura.addActionListener(accionesMenu);
		formPrincipal.mntmAltaCaja.addActionListener(accionesMenu);
		formPrincipal.mntmUsuarios.addActionListener(accionesMenu);
	}
	
	ActionListener accionesMenu=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==formPrincipal.mntmAltaClientes)
			{
				try {
					altaClienteController=new AltaClienteController(formPrincipal,true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			if(e.getSource()==formPrincipal.mntmEditarCliente)
			{
				try {
					altaClienteController=new AltaClienteController(formPrincipal,false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			
			if(e.getSource()==formPrincipal.mntmAltaIva)
			{
				try {
					altaIva=new AltaIvaController(formPrincipal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			if(e.getSource()==formPrincipal.mntmListarClientes)
			{
				try {
					listarClientesController=new ListarClientesController(formPrincipal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			
			if(e.getSource()==formPrincipal.mntmNuevoProveedor)
			{
				try
				{
					altaProveedoresController=new AltaProveedoresController(formPrincipal,false);
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			if(e.getSource()==formPrincipal.mntmEditarProveedor)
			{
				try
				{
					altaProveedoresController=new AltaProveedoresController(formPrincipal,true);
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			if(e.getSource()==formPrincipal.mntmListarProveedor)
			{
				listarProveedoresController=new ListarProveedoresController(formPrincipal);
				
			}
			if(e.getSource()==formPrincipal.mntmCategorias)
			{
				categoriasController=new CategoriasController(formPrincipal);
			}
			
			if(e.getSource()==formPrincipal.mntmAltaProducto)
			{
				cargarProductoController=new CargarProductoController(formPrincipal,false);
			}
			if(e.getSource()==formPrincipal.mntmEditarProducto)
			{
				cargarProductoController=new CargarProductoController(formPrincipal,true);
			}
			
			if(e.getSource()==formPrincipal.mntmIngresarStock)
			{
				stockController=new StockController(formPrincipal);
			}
			if(e.getSource()==formPrincipal.mntmListarProductos)
			{
				listarProductosController=new ListarProductosController(formPrincipal);
			}
			
			if(e.getSource()==formPrincipal.mntmTipoFactura)
			{
				tipoFacturaController=new AltaTipoFacturaController();
			}
			
			if(e.getSource()==formPrincipal.mntmAltaCaja)
			{
				altaCajaController=new AltaCajasController(formPrincipal);
			}
			
			if(e.getSource()==formPrincipal.mntmUsuarios)
			{
				usuarioNuevoController=new NuevoUsuarioController(formPrincipal);
			}
			
		}
	};
	
	

}
