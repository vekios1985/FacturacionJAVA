package org.facturacion.principal.controllers.productos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.facturacion.principal.models.Categoria;
import org.facturacion.principal.models.ItemProducto;
import org.facturacion.principal.models.Proveedor;
import org.facturacion.principal.services.productos.IProductoService;
import org.facturacion.principal.services.productos.ProductoService;
import org.facturacion.principal.services.proveedores.IProveedorService;
import org.facturacion.principal.services.proveedores.ProveedorService;
import org.facturacion.principal.utils.GenerarExcel;
import org.facturacion.principal.utils.GenerarPdf;
import org.facturacion.principal.vista.FormPrincipal;
import org.facturacion.principal.vista.productos.FormListarProductos;

public class ListarProductosController {

	private FormListarProductos formProductos;

	private IProductoService serviceProducto;
	private IProveedorService serviceProveedor;
	String[] columnas = { "Nombre", "Categoria", "Proveedor", "Stock", "Precio" };
	private DefaultTableModel tableModel;

	public ListarProductosController(FormPrincipal principal) {
		try {
			formProductos = new FormListarProductos(principal, true);
			CargarTable();
			formProductos.rdbtnCategorias.addItemListener(it);
			formProductos.rdbtnNombre.addItemListener(it);
			formProductos.rdbtnProveedor.addItemListener(it);
			formProductos.rdbtnTodos.addItemListener(it);
			formProductos.rdbtnTodos.setSelected(true);
			serviceProducto = new ProductoService();
			serviceProveedor = new ProveedorService();

			formProductos.btnListar.addActionListener(ac);
			formProductos.btnExcel.addActionListener(ac);
			formProductos.btnPdf.addActionListener(ac);

			formProductos.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 0);
		}
	}

	void CargarTable() {
		tableModel = new DefaultTableModel();
		for (String s : columnas) {
			tableModel.addColumn(s);
		}
		formProductos.table.setModel(tableModel);
	}

	ItemListener it = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			tableModel.setRowCount(0);
			if (formProductos.rdbtnTodos.isSelected()) {
				formProductos.textFieldNombre.setEnabled(false);
				formProductos.comboBoxCategoria.setEnabled(false);
				formProductos.comboBoxProveedor.setEnabled(false);
			}
			if (formProductos.rdbtnCategorias.isSelected()) {
				formProductos.textFieldNombre.setEnabled(false);
				formProductos.comboBoxCategoria.setEnabled(true);
				formProductos.comboBoxProveedor.setEnabled(false);
				cargarComboCategoria();
			}
			if (formProductos.rdbtnNombre.isSelected()) {
				formProductos.textFieldNombre.setEnabled(true);
				formProductos.comboBoxCategoria.setEnabled(false);
				formProductos.comboBoxProveedor.setEnabled(false);
			}
			if (formProductos.rdbtnProveedor.isSelected()) {
				formProductos.textFieldNombre.setEnabled(false);
				formProductos.comboBoxCategoria.setEnabled(false);
				formProductos.comboBoxProveedor.setEnabled(true);
				cargarComboProveedor();
			}
		}
	};

	@SuppressWarnings("unchecked")
	private void cargarComboProveedor() {
		formProductos.comboBoxProveedor.removeAllItems();
		try {
			for (Proveedor p : serviceProveedor.findAll()) {
				formProductos.comboBoxProveedor.addItem(p);
			}
			formProductos.comboBoxProveedor.setSelectedItem(null);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(formProductos, ex.getMessage(), "Error", 0);
		}
	}

	@SuppressWarnings("unchecked")
	private void cargarComboCategoria() {
		formProductos.comboBoxCategoria.removeAllItems();
		try {
			for (Categoria c : serviceProducto.findAllCategorias()) {
				formProductos.comboBoxCategoria.addItem(c);
			}
			formProductos.comboBoxCategoria.setSelectedItem(null);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(formProductos, ex.getMessage(), "Error", 0);
		}
	}

	ActionListener ac = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == formProductos.btnListar) {
				tableModel.setRowCount(0);
				if (formProductos.rdbtnTodos.isSelected()) {
					try {
						for (ItemProducto item : serviceProducto.findAllItems()) {

							tableModel.addRow(new Object[] { item.getProducto().getNombre(),
									item.getProducto().getCategoria().getNombre(), item.getProveedor().getNombre(),
									item.getStock(), item.getPrecio() });
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(formProductos, ex.getMessage(), "Error", 0);
					}
				}

				if (formProductos.rdbtnNombre.isSelected()) {
					String texto = formProductos.textFieldNombre.getText();

					try {
						for (ItemProducto item : serviceProducto.findItemsByName(texto)) {

							tableModel.addRow(new Object[] { item.getProducto().getNombre(),
									item.getProducto().getCategoria().getNombre(), item.getProveedor().getNombre(),
									item.getStock(), item.getPrecio() });
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(formProductos, ex.getMessage(), "Error", 0);
					}
				}

				if (formProductos.rdbtnCategorias.isSelected()) {
					Categoria categoria = (Categoria) formProductos.comboBoxCategoria.getSelectedItem();
					if (categoria != null) {

						try {
							for (ItemProducto item : serviceProducto.findItemsByCategoria(categoria)) {

								tableModel.addRow(new Object[] { item.getProducto().getNombre(),
										item.getProducto().getCategoria().getNombre(), item.getProveedor().getNombre(),
										item.getStock(), item.getPrecio() });
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(formProductos, ex.getMessage(), "Error", 0);
						}
					}else
					{
						JOptionPane.showMessageDialog(formProductos,"Seleccione una categoria", "Error", 0);
					}
				}
				
				if (formProductos.rdbtnProveedor.isSelected()) {
					Proveedor proveedor = (Proveedor) formProductos.comboBoxProveedor.getSelectedItem();
					if (proveedor != null) {

						try {
							for (ItemProducto item : serviceProducto.findItemsByProveedor(proveedor)) {

								tableModel.addRow(new Object[] { item.getProducto().getNombre(),
										item.getProducto().getCategoria().getNombre(), item.getProveedor().getNombre(),
										item.getStock(), item.getPrecio() });
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(formProductos, ex.getMessage(), "Error", 0);
						}
					}else
					{
						JOptionPane.showMessageDialog(formProductos,"Seleccione un proveedor", "Error", 0);
					}
				}
				
				
			}
			if(e.getSource()==formProductos.btnExcel)
				{
					GenerarExcel.CrearDocumento(tableModel);
				}
				
				if(e.getSource()==formProductos.btnPdf)
				{
					GenerarPdf.exportarJTableAPDF(formProductos.table);
				}
		}
	};

}
