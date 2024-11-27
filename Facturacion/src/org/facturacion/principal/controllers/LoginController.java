package org.facturacion.principal.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.services.ILoginService;
import org.facturacion.principal.services.LoginService;
import org.facturacion.principal.vista.FormLogin;
import org.facturacion.principal.vista.FormPrincipal;

public class LoginController {

	ILoginService service;
	public FormLogin login;
	public Usuario usuario;

	public LoginController(FormPrincipal formPrincipal, boolean modal) {
		service=new LoginService();
		usuario=null;
		login = new FormLogin(formPrincipal, modal);
		inciarLogin();
		login.setVisible(true);
		
		
	}
	
	public void inciarLogin()
	{
		login.btnIngresar.addActionListener(ac);
		login.btnSalir.addActionListener(ac);
			}
	
	ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==login.btnSalir)
			{
				
				System.exit(0);
			}
			if(e.getSource()==login.btnIngresar)
			{
				usuario=validarUsuario();
				if(usuario!=null)
					login.dispose();
			}
			
		}
	};

	public Usuario validarUsuario() {
		
		String username = "vekios";
		char password[];
		
			try {
				//username=login.textFieldUsuario.getText();
				//username.trim();
				if(username.length()<3||username.isEmpty()||username.isBlank())
				{
					JOptionPane.showMessageDialog(login, "El usuario no puede ser menor a 3 caracteres", "Error", 0);
				}
				else
				{
					//password=login.passwordFieldPassword.getPassword();
					
					
					/*if(password.length<3)
					{
						JOptionPane.showMessageDialog(login, "El campo de contraseña no puede estar vacio o menor a 3 caracteres", "Error", 0);
					}else
					{
						//String newPassword=new String(password);
						String newPassword="12345";
						usuario = service.validarUsuario(username, newPassword);
						
							
					}*/
					String newPassword="12345";
					usuario = service.validarUsuario(username, newPassword);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(login, e.getMessage(), "Error", 0);
			}
		

		return usuario;
	}
	
	

}
