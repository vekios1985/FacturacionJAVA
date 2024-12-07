package org.facturacion.principal.controllers.clientes;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.facturacion.principal.models.Cliente;
import org.facturacion.principal.models.Iva;
import org.facturacion.principal.services.clientes.ClienteService;
import org.facturacion.principal.services.clientes.IClientesService;
import org.facturacion.principal.utils.Texto;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.clientes.FormAltaCliente;

public class AltaClienteController {
	
	
	@SuppressWarnings("unused")
	private FormPrincipal principal;
	private FormAltaCliente altaCliente;
	private IClientesService clienteServive;
	private boolean nuevo_cliente;
	Cliente cliente=null;
	
	public AltaClienteController(FormPrincipal principal,boolean nuevo_cliente) 
	{
		
		try
		{this.principal=principal;
		this.nuevo_cliente=nuevo_cliente;
		altaCliente=new FormAltaCliente(principal, true);
		setInicio();
		clienteServive=new ClienteService();
		limpiarCampos();
		cargarCombo();
		altaCliente.btnGuardar.addActionListener(accion);
		altaCliente.btnBuscar.addActionListener(accion);
		if (nuevo_cliente) {
			altaCliente.lblTexto.setText("Alta Cliente");

		} else {
			altaCliente.lblTexto.setText("Editar Cliente");
		}
		altaCliente.setVisible(true);
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 0);
		}
		
	}
	
	void setInicio()
	{
		if(nuevo_cliente)
		{
			altaCliente.btnGuardar.setEnabled(true);
			altaCliente.btnBuscar.setEnabled(false);
			altaCliente.btnBuscar.setVisible(false);
		}
		else
		{
			if(cliente==null)
			{
				altaCliente.btnGuardar.setEnabled(false);
				altaCliente.textFieldApellido.setEnabled(false);
				altaCliente.textFieldDireccion.setEnabled(false);
				//altaCliente.textFieldDni.setEnabled(false);
				altaCliente.textFieldEmail.setEnabled(false);
				altaCliente.textFieldLocalidad.setEnabled(false);
				altaCliente.textFieldNombre.setEnabled(false);
				altaCliente.textFieldTelefono.setEnabled(false);
				altaCliente.comboBox.setEnabled(false);
			}
			else
			{
				altaCliente.btnGuardar.setEnabled(true);
				altaCliente.btnGuardar.setEnabled(true);
				altaCliente.textFieldApellido.setEnabled(true);
				altaCliente.textFieldDireccion.setEnabled(true);
				//altaCliente.textFieldDni.setEnabled(false);
				altaCliente.textFieldEmail.setEnabled(true);
				altaCliente.textFieldLocalidad.setEnabled(true);
				altaCliente.textFieldNombre.setEnabled(true);
				altaCliente.textFieldTelefono.setEnabled(true);
				altaCliente.comboBox.setEnabled(true);
			}
			altaCliente.btnBuscar.setEnabled(true);
			altaCliente.btnBuscar.setVisible(true);
		}
	}
	
	ActionListener accion=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==altaCliente.btnGuardar)
			{
				try {
					String nombre=Texto.normalizar(altaCliente.textFieldNombre.getText());
					String apellido=Texto.normalizar(altaCliente.textFieldApellido.getText());
					String telefono=Texto.normalizar(altaCliente.textFieldTelefono.getText());
					String direccion=Texto.normalizar(altaCliente.textFieldDireccion.getText());
					String localidad=Texto.normalizar(altaCliente.textFieldLocalidad.getText());
					String email=Texto.validarEmail(altaCliente.textFieldEmail.getText());
					
					int idIva=altaCliente.comboBox.getSelectedIndex();
					if(idIva>=0)
					{
						Iva iva=(Iva)altaCliente.comboBox.getSelectedItem();
						if(cliente==null)
						{
							Integer dni=Texto.validarDni(altaCliente.textFieldDni.getText());
							cliente=new Cliente(nombre, apellido, telefono, direccion, localidad, email, dni, iva);
						}
						else
						{
							cliente.setApellido(apellido);
							cliente.setDireccion(direccion);
							cliente.setEmail(email);
							cliente.setIva(iva);
							cliente.setNombre(nombre);
							cliente.setLocalidad(localidad);
							cliente.setTelefono(telefono);
						}
						try
						{
							clienteServive.save(cliente);
							limpiarCampos();
							cliente=null;
							setInicio();
							JOptionPane.showMessageDialog(altaCliente,"Cliente guardado con exito", "Exito", 1);
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(altaCliente,ex.getMessage(), "Error", 0);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(altaCliente,"Debe seleccionar tipo de IVA", "Error", 0);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(altaCliente, e1.getMessage(), "Error", 0);
				}
				
			}
			if(e.getSource()==altaCliente.btnBuscar)
			{
				try
				{
					Integer dni=Texto.validarDni(altaCliente.textFieldDni.getText());
					cliente=clienteServive.findByDni(dni);
					if(cliente!=null)
					{
						setInicio();
						altaCliente.textFieldNombre.setText(cliente.getNombre());
						altaCliente.textFieldApellido.setText(cliente.getApellido());
						altaCliente.textFieldDireccion.setText(cliente.getDireccion());
						altaCliente.textFieldEmail.setText(cliente.getEmail());
						altaCliente.textFieldLocalidad.setText(cliente.getLocalidad());
						altaCliente.textFieldTelefono.setText(cliente.getTelefono());
						altaCliente.comboBox.setSelectedItem(cliente.getIva());
						
					}
					else
					{
						JOptionPane.showMessageDialog(altaCliente,"El dni ingresado no coincide con ningun cliente", "Error", 0);
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(altaCliente, ex.getMessage(), "Error", 0);
				}
			}
			
		}
	};
	
	void limpiarCampos()
	{
		altaCliente.textFieldNombre.setText("");
		altaCliente.textFieldApellido.setText("");
		altaCliente.textFieldTelefono.setText("");
		altaCliente.textFieldDireccion.setText("");
		altaCliente.textFieldLocalidad.setText("");
		altaCliente.textFieldEmail.setText("");
		altaCliente.textFieldDni.setText("");
		altaCliente.comboBox.setSelectedIndex(-1);
	}
	
	void cargarCombo() throws Exception
	{
		for(Iva iva:clienteServive.listarIva())
		{
			altaCliente.comboBox.addItem(iva);
		}
		altaCliente.comboBox.setSelectedIndex(-1);
	}

}
