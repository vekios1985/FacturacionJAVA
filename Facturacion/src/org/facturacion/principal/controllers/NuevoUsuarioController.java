package org.facturacion.principal.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


import org.facturacion.principal.models.Role;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.services.ILoginService;
import org.facturacion.principal.services.LoginService;
import org.facturacion.principal.utils.Texto;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.administracion.FormUsuarios;

public class NuevoUsuarioController {

	FormUsuarios usuarios;
	ILoginService service;
	Usuario user=null;

	public NuevoUsuarioController(FormPrincipal principal) {

		try {
			usuarios = new FormUsuarios(principal, true);
			service = new LoginService();
			cargarColumnas();
			ListarUsuarios();
			cargarRoles();
			usuarios.btnGuardar.addActionListener(ac);
			usuarios.btnEditar.addActionListener(ac);

			usuarios.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}

	}

	@SuppressWarnings("unchecked")
	private void cargarRoles() {
		usuarios.comboBoxRole.removeAllItems();
		try {
			for (Role r : service.listarRoles()) {
				usuarios.comboBoxRole.addItem(r);
			}
			usuarios.comboBoxRole.setSelectedItem(null);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(usuarios, ex.getMessage(), "Error", 0);
		}
	}

	private void cargarColumnas() {
		usuarios.modeloTabla.addColumn("Nombre y Apellido");
		usuarios.modeloTabla.addColumn("Username");
		usuarios.modeloTabla.addColumn("DNI");
		usuarios.modeloTabla.addColumn("Role");
	}

	private void ListarUsuarios() {
		usuarios.modeloTabla.setRowCount(0);
		try {
			for (Usuario u : service.findAllUser()) {
				usuarios.modeloTabla.addRow(new Object[] { u.getApellido() + " " + u.getNombre(), u.getUsername(),
						u.getDni(), u.getRole().getNombre() });
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(usuarios, ex.getMessage(), "Error", 0);
		}
	}

	ActionListener ac = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == usuarios.btnGuardar) {
				try {
					
					String inputDni = usuarios.textFieldDni.getText();
					String nombre = Texto.normalizar(usuarios.textFieldNombre.getText());
					String apellido = Texto.normalizar(usuarios.textFieldApellido.getText());
					String username = Texto.normalizar(usuarios.textFieldUsername.getText());
					String pass = usuarios.passwordField.getPassword().toString();
					Role role = (Role) usuarios.comboBoxRole.getSelectedItem();
					if (inputDni == null || nombre == null || apellido == null || username == null
							|| role == null) {
						JOptionPane.showMessageDialog(usuarios, "Debe completar todos los campos y seleccionar un Role",
								"Error", 0);
					} else {

						Integer dni = Integer.parseInt(inputDni);
						if(user==null) {
							if(pass==null)
							{
								throw new Exception ("No ha ingresado una contraseña");
							}
							else
							{
								user = new Usuario(role, username, pass, nombre, apellido, dni);
							}
						}
						
						else
						{
							user.setApellido(apellido);
							user.setRole(role);
							user.setNombre(nombre);
							user.setUsername(username);
							
						}
						service.saveUser(user);
						JOptionPane.showMessageDialog(usuarios, "Usuario guardado con exito", "Exito", 0);
						usuarios.textFieldDni.setText("");
						usuarios.textFieldNombre.setText("");
						usuarios.textFieldApellido.setText("");
						usuarios.textFieldUsername.setText("");
						usuarios.passwordField.setText("");
						usuarios.comboBoxRole.setSelectedItem(null);
						ListarUsuarios();
						user=null;
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(usuarios, ex.getMessage(), "Error", 0);
				}
			}
			
			if(e.getSource()==usuarios.btnEditar)
			{
				int selected=usuarios.table.getSelectedRow();
				if(selected==-1)
				{
					JOptionPane.showMessageDialog(usuarios, "No ha seleccionado un usuario para editar", "Error", 0);
				}
				else
				{
					String n_dni=usuarios.modeloTabla.getValueAt(selected, 2).toString();
					
					try {
						
						Integer dni=Integer.parseInt(n_dni);
						user=service.findUsuarioByDni(dni);
						usuarios.textFieldDni.setText(user.getDni().toString());
						usuarios.textFieldNombre.setText(user.getNombre());
						usuarios.textFieldApellido.setText(user.getApellido());
						usuarios.textFieldUsername.setText(user.getUsername());
						usuarios.passwordField.setText("");
						usuarios.comboBoxRole.setSelectedItem(user.getRole());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(usuarios, e1.getMessage(), "Error", 0);
					}
				}
			}

		}
	};

}
