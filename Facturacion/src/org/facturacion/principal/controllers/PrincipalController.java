package org.facturacion.principal.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.facturacion.principal.controllers.clientes.AltaClienteController;
import org.facturacion.principal.controllers.clientes.AltaIvaController;
import org.facturacion.principal.controllers.clientes.ListarClientesController;
import org.facturacion.principal.models.Role;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.services.ILoginService;
import org.facturacion.principal.services.LoginService;
import org.facturacion.principal.vista.FormPrincipal;

@SuppressWarnings("unused")
public class PrincipalController {

	public FormPrincipal formPrincipal;

	private AltaClienteController altaClienteController;

	private AltaIvaController altaIva;

	private ListarClientesController listarClientesController;
	private LoginController controllerLogin;
	public Usuario usuario = null;

	private ILoginService service;

	public PrincipalController(FormPrincipal principal) {
		formPrincipal = principal;
		formPrincipal.setVisible(true);
		service = new LoginService();
		
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
			
			
		}
	};
	
	

}
