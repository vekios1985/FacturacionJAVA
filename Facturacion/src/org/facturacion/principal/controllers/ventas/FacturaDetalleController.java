package org.facturacion.principal.controllers.ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.facturacion.principal.models.Factura;
import org.facturacion.principal.models.ItemFactura;
import org.facturacion.principal.models.TipoFactura;
import org.facturacion.principal.models.Venta;
import org.facturacion.principal.services.facturacion.FacturaService;
import org.facturacion.principal.services.facturacion.IFacturaService;
import org.facturacion.principal.utils.GenerarFactura;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.ventas.FormFacturaDetalle;

public class FacturaDetalleController {
	
	private FormFacturaDetalle formDetalle;
	private DefaultTableModel model;
	private IFacturaService service;
	private Venta venta;
	
	public FacturaDetalleController(FormPrincipal principal) {

		try {
			venta=null;
			formDetalle=new FormFacturaDetalle(principal);
			service=new FacturaService();
			model=new DefaultTableModel();
			formDetalle.table.setModel(model);
			CargarTabla();
			CargarCombo();
			formDetalle.btnBuscar.addActionListener(ac);
			formDetalle.btnPdf.addActionListener(ac);
			formDetalle.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(principal, e.getMessage(), "Error", 0);
		}
	}
	
	private void CargarTabla()
	{
		model.addColumn("#");
		model.addColumn("Codigo");
		model.addColumn("Descripcion");
		model.addColumn("Cantidad");
		model.addColumn("Unitario");
		model.addColumn("Importe");

		formDetalle.table.getColumnModel().getColumn(0).setPreferredWidth(25);
		formDetalle.table.getColumnModel().getColumn(1).setPreferredWidth(80);
		formDetalle.table.getColumnModel().getColumn(2).setPreferredWidth(485);
		formDetalle.table.getColumnModel().getColumn(3).setPreferredWidth(60);
		formDetalle.table.getColumnModel().getColumn(4).setPreferredWidth(100);
		formDetalle.table.getColumnModel().getColumn(5).setPreferredWidth(100);
	}
	
	@SuppressWarnings("unchecked")
	private void CargarCombo()
	{
		formDetalle.comboBox.removeAllItems();
		try {
			for(TipoFactura tipo:service.findAllTipoFactura())
			{
				formDetalle.comboBox.addItem(tipo);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(formDetalle, e.getMessage(), "Error", 0);
		}
		
		formDetalle.comboBox.setSelectedItem(null);
	}
	
	private void itemClear()
	{
		CargarCombo();
		formDetalle.textField.setText("");
		formDetalle.lblClienteValor.setText("...");
		formDetalle.lblDescuentoValor.setText("...");
		formDetalle.lblFechaValor.setText("...");
		formDetalle.lblObservacionValor.setText("");
		formDetalle.lblTotalValor.setText("...");
		model.setRowCount(0);
		venta=null;
	}
	
	ActionListener ac=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==formDetalle.btnBuscar)
			{
				TipoFactura tipo=(TipoFactura) formDetalle.comboBox.getSelectedItem();
				if(tipo==null)
				{
					JOptionPane.showMessageDialog(formDetalle, "Debe seleccionar un tipo de factura", "Error", 0);
				}
				else
				{
					String numero=formDetalle.textField.getText();
					if(numero==null)
					{
						JOptionPane.showMessageDialog(formDetalle, "Debe ingresar un numero de factura", "Error", 0);
					}
					else
					{
						try {
							Factura factura=service.findFacturaNumero(numero);
							if(factura!=null)
							{
								venta=service.findVenta(factura);
								if(venta!=null)
								{
									formDetalle.lblClienteValor.setText(factura.getCliente().getNombre()+" "+factura.getCliente().getApellido());
									formDetalle.lblDescuentoValor.setText(factura.getDescuento().toString());
									formDetalle.lblFechaValor.setText(factura.getFecha().toString());
									formDetalle.lblObservacionValor.setText(factura.getObservacion());
									formDetalle.lblTotalValor.setText(venta.getTotal().toString());
									int itemCount=0;
									for(ItemFactura item:venta.getItems())
									{
										itemCount++;
										model.addRow(new Object[] {itemCount, item.getProducto().getCodigo(),
					item.getProducto().getNombre(), item.getCantidad(), item.getPrecio(), item.getMonto() });
									}
								}
								
								
							}
							else
							{
								JOptionPane.showMessageDialog(formDetalle, "Factura no encontrada", "Error", 0);
								itemClear();
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(formDetalle, e1.getMessage(), "Error", 0);
						}
					}
				}
			}
			
			if(e.getSource()==formDetalle.btnPdf)
			{
				if(model.getRowCount()>0)
				{
					GenerarFactura.exportarJTableAPDF(formDetalle.table, venta);
				}
			}
			
		}
	};

}
