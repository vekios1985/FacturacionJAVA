package org.facturacion.principal.controllers.ventas;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.models.Venta;
import org.facturacion.principal.services.facturacion.FacturaService;
import org.facturacion.principal.services.facturacion.IFacturaService;
import org.facturacion.principal.utils.GenerarExcel;

import org.facturacion.principal.utils.GenerarPdf;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.ventas.FormListarFacturas;

public class ListarFacturasController {
	
	private FormListarFacturas listarFacturas;
	private DefaultTableModel modelo;
	private IFacturaService serviceFactura;
	
	public ListarFacturasController(FormPrincipal principal) {
		
		try {
			serviceFactura=new FacturaService();
			listarFacturas=new FormListarFacturas(principal);
			modelo=new DefaultTableModel();
			Iniciar();
			cargarCombo();
			
			listarFacturas.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(listarFacturas, e.getMessage(), "Error", 0);
		}
	}
	
	
	private void Iniciar()
	{
		listarFacturas.rdbtnCliente.addActionListener(ac);
		listarFacturas.rdbtnFecha.addActionListener(ac);
		listarFacturas.rdbtnTipoFactura.addActionListener(ac);
		listarFacturas.rdbtnTodas.addActionListener(ac);
		listarFacturas.btnExcel.addActionListener(ac);
		listarFacturas.btnListar.addActionListener(ac);
		listarFacturas.btnPdf.addActionListener(ac);
		listarFacturas.rdbtnTodas.setSelected(true);
		listarFacturas.dateChooser.setEnabled(false);
		listarFacturas.textField.setEnabled(false);
		listarFacturas.comboBox.setEnabled(false);
		listarFacturas.table.setModel(modelo);
		modelo.addColumn("Tipo Factura");
		modelo.addColumn("Numero Factura");
		modelo.addColumn("Fecha");
		modelo.addColumn("Cliente");		
		modelo.addColumn("Monto");
	}
	
	@SuppressWarnings("unchecked")
	private void cargarCombo()
	{
		listarFacturas.comboBox.removeAllItems();
		try {
			for(TipoFactura tipo:serviceFactura.findAllTipoFactura())
			{
				listarFacturas.comboBox.addItem(tipo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(listarFacturas, e.getMessage(), "Error", 0);
		}
		listarFacturas.comboBox.setSelectedItem(null);
	}
	
	ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==listarFacturas.rdbtnTodas)
			{
				if(listarFacturas.rdbtnTodas.isSelected())
				{
					listarFacturas.dateChooser.setEnabled(false);
					listarFacturas.textField.setEnabled(false);
					listarFacturas.comboBox.setEnabled(false);
				}
			}
			if(e.getSource()==listarFacturas.rdbtnFecha)
			{
				if(listarFacturas.rdbtnFecha.isSelected())
				{
					listarFacturas.dateChooser.setEnabled(true);
					listarFacturas.textField.setEnabled(false);
					listarFacturas.comboBox.setEnabled(false);
				}
			}
			if(e.getSource()==listarFacturas.rdbtnCliente)
			{
				if(listarFacturas.rdbtnCliente.isSelected())
				{
					listarFacturas.dateChooser.setEnabled(false);
					listarFacturas.textField.setEnabled(true);
					listarFacturas.comboBox.setEnabled(false);
				}
			}
			if(e.getSource()==listarFacturas.rdbtnTipoFactura)
			{
				if(listarFacturas.rdbtnTipoFactura.isSelected())
				{
					listarFacturas.dateChooser.setEnabled(false);
					listarFacturas.textField.setEnabled(false);
					listarFacturas.comboBox.setEnabled(true);
				}
			}
			if(e.getSource()==listarFacturas.btnListar)
			{
				modelo.setRowCount(0);
				if(listarFacturas.rdbtnTodas.isSelected())
				{
					try
					{
						for(Factura factura:serviceFactura.findAllFactura())
						{
							Venta venta=serviceFactura.getVenta(factura);
							if(venta !=null)		
							modelo.addRow(new Object[] {factura.getTipoFactura().getTipo(),factura.getNumero(),factura.getFecha(),
									factura.getCliente().getNombre()+" "+factura.getCliente().getApellido(),
									venta.getTotal()});
						
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(listarFacturas, ex.getMessage(), "Error", 0);
					}
				}
				else if(listarFacturas.rdbtnFecha.isSelected()) {
					LocalDate fecha=listarFacturas.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					try
					{
						
						for(Factura factura:serviceFactura.findFacturaFecha(fecha))
						{
							Venta venta=serviceFactura.getVenta(factura);
							if(venta !=null)		
							modelo.addRow(new Object[] {factura.getTipoFactura().getTipo(),factura.getNumero(),factura.getFecha(),
									factura.getCliente().getNombre()+" "+factura.getCliente().getApellido(),
									venta.getTotal()});
						
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(listarFacturas, ex.getMessage(), "Error", 0);
					}
				}
				else if(listarFacturas.rdbtnCliente.isSelected())
				{
					
					try
					{
						Integer numero=Integer.parseInt(listarFacturas.textField.getText());
						for(Factura factura:serviceFactura.findFacturaCliente(numero))
						{
							Venta venta=serviceFactura.getVenta(factura);
							if(venta !=null)		
							modelo.addRow(new Object[] {factura.getTipoFactura().getTipo(),factura.getNumero(),factura.getFecha(),
									factura.getCliente().getNombre()+" "+factura.getCliente().getApellido(),
									venta.getTotal()});
						
						}
						listarFacturas.textField.setText("");
					}
					
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(listarFacturas, ex.getMessage(), "Error", 0);
					}
				}
				else if(listarFacturas.rdbtnTipoFactura.isSelected())
				{
					try
					{
						TipoFactura tipo=(TipoFactura) listarFacturas.comboBox.getSelectedItem();
						if(tipo==null)
						{
							JOptionPane.showMessageDialog(listarFacturas, "Debe seleccionar un tipo de factura", "Error", 0);
						}
						else
						{
							for(Factura factura:serviceFactura.findFacturaTipo(tipo))
							{
								Venta venta=serviceFactura.getVenta(factura);
								if(venta !=null)		
								modelo.addRow(new Object[] {factura.getTipoFactura().getTipo(),factura.getNumero(),factura.getFecha(),
										factura.getCliente().getNombre()+" "+factura.getCliente().getApellido(),
										venta.getTotal()});
							
							}
							listarFacturas.comboBox.setSelectedItem(null);
						}
					}
					
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(listarFacturas, ex.getMessage(), "Error", 0);
					}
				}
			}
			
			if(e.getSource()==listarFacturas.btnExcel)
			{
				GenerarExcel.CrearDocumento(modelo);
			}
			if(e.getSource()==listarFacturas.btnPdf)
			{
				GenerarPdf.exportarJTableAPDF(listarFacturas.table);
				
			}
			
		}
	};

}
