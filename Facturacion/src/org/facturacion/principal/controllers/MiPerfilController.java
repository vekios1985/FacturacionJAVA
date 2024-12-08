package org.facturacion.principal.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.services.ILoginService;
import org.facturacion.principal.services.LoginService;
import org.facturacion.principal.vista.FormMiPerfil;
import org.facturacion.principal.vista.FormPrincipal;

public class MiPerfilController {
	
	FormPrincipal pri;
	FormMiPerfil miperfil;
	ILoginService service;
	Usuario user;
	DefaultComboBoxModel<String>modelo;
	
	public MiPerfilController(FormPrincipal principal,Usuario user) {
		
		try {
			pri=principal;
			miperfil=new FormMiPerfil(principal);
			modelo=new DefaultComboBoxModel<String>();
			service=new LoginService();
			this.user=user;
			miperfil.btnSetPassword.addActionListener(ac);
			CargarDatos();
			miperfil.comboBox.addItemListener(it);
			miperfil.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(principal, e.getMessage(), "Error", 0);
		}
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void CargarDatos()
	{
		miperfil.lblApellidoValor.setText(user.getApellido());
		miperfil.lblNombreValor.setText(user.getNombre());
		miperfil.lblDniValor.setText(user.getDni().toString());
		miperfil.lblRoleValor.setText(user.getRole().toString());
		miperfil.lblUsuarioValor.setText(user.getUsername());
		
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			System.out.println(info.getClassName());
			modelo.addElement(info.getName());
		}
		miperfil.comboBox.setModel(modelo);
		miperfil.comboBox.setSelectedItem(null);
	}
	
	ItemListener it=new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if (e.getStateChange() == ItemEvent.SELECTED) {
				LookAndFeelInfo info = UIManager.getInstalledLookAndFeels()[miperfil.comboBox.getSelectedIndex()];
				try {
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(pri);  // Cambiado a Cambio.this
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		}
	};
	
	ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==miperfil.btnSetPassword)
			{
				if(JOptionPane.showOptionDialog(miperfil, "Desea cambiar la clave?", "Confirma?", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, null, null, e)==JOptionPane.YES_OPTION)
				{
					String pass1=JOptionPane.showInputDialog(miperfil, "Ingrese la nueva contraseña", "Ingreso de clave", 1);
					String pass2=JOptionPane.showInputDialog(miperfil, "Confirme la nueva contraseña", "Confirmacion de clave", 1);
					if(pass1.equals(pass2))
					{
						try {
							user.setPassword(pass1);
							service.saveUser(user);
							JOptionPane.showMessageDialog(miperfil,"Las contraseña se cambio con exito", "Exito", 1);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(miperfil,e1.getMessage(), "Error", 0);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(miperfil,"Las contraseñas no coinciden", "Error", 0);
					}
				}
			}
		}
	};

}
