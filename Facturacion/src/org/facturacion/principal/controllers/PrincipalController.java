package org.facturacion.principal.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import org.facturacion.principal.controllers.ventas.FacturaDetalleController;
import org.facturacion.principal.controllers.ventas.ListarFacturasController;
import org.facturacion.principal.controllers.ventas.VentasController;
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
	private AltaCajasController altaCajaController;
	private NuevoUsuarioController usuarioNuevoController;
	private ListarFacturasController listarFacturasController;
	private FacturaDetalleController detalleController;
	private VentasController ventasController;
	private MiPerfilController miPerfil;
	public Usuario usuario = null;

	private ILoginService service;

	public PrincipalController(FormPrincipal principal) {

		try {
			formPrincipal = principal;
			formPrincipal.setVisible(true);
			iniciarLogin();
			deshabilitar();

			iniciarController();
			setActionListener();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
		// usuario=service.

	}
	
	void iniciarLogin()throws Exception
	{
		
			service=new LoginService();
		
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

		facturacion = new FacturacionController(formPrincipal, usuario);
		

	}

	public void habilitar(Usuario usuario) {
		// Falta agregar la habilitacion de roles

		try {
			 Role role = service.obtenerRole(usuario);
			 this.formPrincipal.mnNewMenuClientes.setEnabled(true);
			 this.formPrincipal.mnNewMenuInicio.setEnabled(true);
			 if(role.getNombre().equals("ROLE_ADMIN"))
			 {
				
				 this.formPrincipal.mnNewMenuProveedores.setEnabled(true);
				 this.formPrincipal.mnNewMenuProductos.setEnabled(true);
				 this.formPrincipal.mnNewMenuVentas.setEnabled(true);
				 this.formPrincipal.mnNewMenuAdministracion.setEnabled(true);
			 }
			 else if(role.getNombre().equals("ROLE_USER"))
			 {
				 this.formPrincipal.mnNewMenuProveedores.setEnabled(false);
				 this.formPrincipal.mnNewMenuProductos.setEnabled(true);
				 this.formPrincipal.mnNewMenuVentas.setEnabled(false);
				 this.formPrincipal.mnNewMenuAdministracion.setEnabled(false);
			 }
			 else if(role.getNombre().equals("ROLE_SUPERVISOR"))
			 {
				 this.formPrincipal.mnNewMenuProveedores.setEnabled(true);
				 this.formPrincipal.mnNewMenuProductos.setEnabled(true);
				 this.formPrincipal.mnNewMenuVentas.setEnabled(true);
				 this.formPrincipal.mnNewMenuAdministracion.setEnabled(false);
			 }
			
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
	}

	void setActionListener() {
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
		formPrincipal.mntmListarFacturas.addActionListener(accionesMenu);
		formPrincipal.mntmBuscarFactura.addActionListener(accionesMenu);
		formPrincipal.mntmVentas.addActionListener(accionesMenu);
		formPrincipal.mntmSalir.addActionListener(accionesMenu);
		formPrincipal.mntmMiPerfil.addActionListener(accionesMenu);
		formPrincipal.mntmCambiarUsuario.addActionListener(accionesMenu);
	}

	ActionListener accionesMenu = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == formPrincipal.mntmAltaClientes) {
				try {
					altaClienteController = new AltaClienteController(formPrincipal, true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			if (e.getSource() == formPrincipal.mntmEditarCliente) {
				try {
					altaClienteController = new AltaClienteController(formPrincipal, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}

			if (e.getSource() == formPrincipal.mntmAltaIva) {
				try {
					altaIva = new AltaIvaController(formPrincipal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			if (e.getSource() == formPrincipal.mntmListarClientes) {
				try {
					listarClientesController = new ListarClientesController(formPrincipal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}

			if (e.getSource() == formPrincipal.mntmNuevoProveedor) {
				try {
					altaProveedoresController = new AltaProveedoresController(formPrincipal, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			if (e.getSource() == formPrincipal.mntmEditarProveedor) {
				try {
					altaProveedoresController = new AltaProveedoresController(formPrincipal, true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formPrincipal, e1.getMessage(), "Error", 0);
				}
			}
			if (e.getSource() == formPrincipal.mntmListarProveedor) {
				listarProveedoresController = new ListarProveedoresController(formPrincipal);

			}
			if (e.getSource() == formPrincipal.mntmCategorias) {
				categoriasController = new CategoriasController(formPrincipal);
			}

			if (e.getSource() == formPrincipal.mntmAltaProducto) {
				cargarProductoController = new CargarProductoController(formPrincipal, false);
			}
			if (e.getSource() == formPrincipal.mntmEditarProducto) {
				cargarProductoController = new CargarProductoController(formPrincipal, true);
			}

			if (e.getSource() == formPrincipal.mntmIngresarStock) {
				stockController = new StockController(formPrincipal);
			}
			if (e.getSource() == formPrincipal.mntmListarProductos) {
				listarProductosController = new ListarProductosController(formPrincipal);
			}

			if (e.getSource() == formPrincipal.mntmTipoFactura) {
				tipoFacturaController = new AltaTipoFacturaController();
			}

			if (e.getSource() == formPrincipal.mntmAltaCaja) {
				altaCajaController = new AltaCajasController(formPrincipal);
			}

			if (e.getSource() == formPrincipal.mntmUsuarios) {
				usuarioNuevoController = new NuevoUsuarioController(formPrincipal);
			}
			if(e.getSource()==formPrincipal.mntmListarFacturas)
			{
				listarFacturasController=new ListarFacturasController(formPrincipal);
			}
			
			if(e.getSource()==formPrincipal.mntmBuscarFactura)
			{
				detalleController=new FacturaDetalleController(formPrincipal);
			}
			if(e.getSource()==formPrincipal.mntmVentas)
			{
				ventasController=new VentasController(formPrincipal);
			}
			if(e.getSource()==formPrincipal.mntmSalir)
			{
				System.exit(0);
			}
			if(e.getSource()==formPrincipal.mntmCambiarUsuario)
			{
				
				    // Cerrar el formulario principal actual
				    formPrincipal.dispose();
				    
				    // Relanzar el login
				    try {
				        // Crear una nueva instancia del LoginController
				        //LoginController nuevoLoginController = new LoginController(null, true);
				        
				        // Obtener el usuario autenticado
				        Usuario nuevoUsuario = controllerLogin.usuario;

				        if (nuevoUsuario != null) {
				            // Si el usuario es válido, iniciar un nuevo formulario principal
				            FormPrincipal nuevoFormPrincipal = new FormPrincipal();
				            new PrincipalController(nuevoFormPrincipal); // Pasar el nuevo formulario al controlador
				        } else {
				            // Si no se autentica un usuario, cerrar la aplicación
				            JOptionPane.showMessageDialog(null, "Usuario no autenticado. Cerrando la aplicación.", "Información", JOptionPane.INFORMATION_MESSAGE);
				            System.exit(0);
				        }
				    } catch (Exception ex) {
				        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				    }
				

			}
			if(e.getSource()==formPrincipal.mntmMiPerfil)
			{
				miPerfil=new MiPerfilController(formPrincipal,usuario);
			}

		}
	};

}
