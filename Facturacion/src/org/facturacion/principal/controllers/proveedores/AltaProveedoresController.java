package org.facturacion.principal.controllers.proveedores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.facturacion.principal.models.Proveedor;
import org.facturacion.principal.services.proveedores.IProveedorService;
import org.facturacion.principal.services.proveedores.ProveedorService;
import org.facturacion.principal.utils.Texto;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.proveedores.FormAgregarProveedor;

public class AltaProveedoresController {
	
	//private FormPrincipal principal;
	private FormAgregarProveedor formProveedor;
	private IProveedorService service;
	private boolean editable;
	Proveedor proveedor;
	
	public AltaProveedoresController(FormPrincipal principal,boolean editable)
	{
		try {
			//this.principal=principal;
			this.formProveedor=new FormAgregarProveedor(principal, true);
			this.editable=editable;
			this.formProveedor.btnGuardar.addActionListener(ac);
			this.formProveedor.btnBuscar.addActionListener(ac);
			service=new ProveedorService();
			setEditable();
			this.formProveedor.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
	}
	
	private void LimpiarCasillas()
	{
		formProveedor.textFieldNombre.setText("");
		formProveedor.textFieldTelefono.setText("");
		formProveedor.textFieldEmail.setText("");
		formProveedor.textFieldDireccion.setText("");
		formProveedor.textFieldCuit.setText("");
	}
	
	private void setEditable()
	{
		if(editable)
		{
			formProveedor.btnBuscar.setVisible(true);
			deshabilitar();
		}
		else
		{
			formProveedor.btnBuscar.setVisible(false);
			habilitar();
		}
	}
	
	private void habilitar()
	{
		formProveedor.textFieldNombre.setEnabled(true);
		formProveedor.textFieldTelefono.setEnabled(true);
		formProveedor.textFieldEmail.setEnabled(true);
		formProveedor.textFieldDireccion.setEnabled(true);
	}
	
	private void deshabilitar()
	{
		formProveedor.textFieldNombre.setEnabled(false);
		formProveedor.textFieldTelefono.setEnabled(false);
		formProveedor.textFieldEmail.setEnabled(false);
		formProveedor.textFieldDireccion.setEnabled(false);
	}
	
	private ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==formProveedor.btnGuardar)
			{
				try {
					String nombre=Texto.normalizar(formProveedor.textFieldNombre.getText());
					String telefono=Texto.normalizar(formProveedor.textFieldTelefono.getText());
					String email=Texto.validarEmail(formProveedor.textFieldEmail.getText());
					String direccion=Texto.normalizar(formProveedor.textFieldDireccion.getText());
					if(editable)
					{
						proveedor.setDireccion(direccion);
						proveedor.setEmail(email);
						proveedor.setNombre(nombre);
						proveedor.setTelefono(telefono);
					}else
					{
						Long cuit=Texto.validarCuit(formProveedor.textFieldCuit.getText());
						proveedor=new Proveedor(nombre, telefono, email, direccion, cuit);
					}
					
					
					
					
					service.save(proveedor);
					JOptionPane.showMessageDialog(formProveedor, "Proveedor guardado con exito", "Exito", 1);
					LimpiarCasillas();
					setEditable();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formProveedor, e1.getMessage(), "Error", 0);
				}
			}
			if(e.getSource()==formProveedor.btnBuscar)
			{
				try {
					
					Long cuit=Texto.validarCuit(formProveedor.textFieldCuit.getText());
					
					proveedor=service.findByCuit(cuit);
					if(proveedor!=null)
					{
						habilitar();
						formProveedor.textFieldNombre.setText(proveedor.getNombre());
						formProveedor.textFieldTelefono.setText(proveedor.getTelefono());
						formProveedor.textFieldEmail.setText(proveedor.getEmail());
						formProveedor.textFieldDireccion.setText(proveedor.getDireccion());
						
					}
					else
					{
						JOptionPane.showMessageDialog(formProveedor, "Proveedor no encontrado", "Error", 0);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(formProveedor, e1.getMessage(), "Error", 0);
				}
			}
		}
	};

}
