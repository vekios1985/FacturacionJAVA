package org.facturacion.principal.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenerarExcel {
	

	public static void CrearDocumento(DefaultTableModel modelo) {
	 Workbook workbook = new XSSFWorkbook();

 
     Sheet sheet = workbook.createSheet("Hoja de Datos");

    
     Row headerRow = sheet.createRow(0);
     

     for(int i=0;i<modelo.getColumnCount();i++)
     {
    	 headerRow.createCell(i).setCellValue(modelo.getColumnName(i));
     }
     
     
     for(int rowcount=0;rowcount<modelo.getRowCount();rowcount++)
     {
    	 Row row = sheet.createRow(rowcount+1);
    	 for(int l=0;l<modelo.getColumnCount();l++)
        	 row.createCell(l).setCellValue(modelo.getValueAt(rowcount,l).toString());
     }

   

     for (int j = 0; j < modelo.getColumnCount(); j++) {
         sheet.autoSizeColumn(j);
     }

     
     JFileChooser fileChooser = new JFileChooser();
     fileChooser.setDialogTitle("Guardar archivo Excel");
     fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos Excel (*.xlsx)", "xlsx"));
     
     int userSelection = fileChooser.showSaveDialog(null);
     
     if (userSelection == JFileChooser.APPROVE_OPTION) {
         File fileToSave = fileChooser.getSelectedFile();
         
        
         String filePath = fileToSave.getAbsolutePath();
         if (!filePath.endsWith(".xlsx")) {
             filePath += ".xlsx";
         }

       
         try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
             workbook.write(fileOut);
             JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente en: " + filePath);
         } catch (IOException e) {
             e.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
     } else {
         JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
     }
     
    
     
     
     
     
     

     // Cerrar el libro de trabajo
     try {
         workbook.close();
     } catch (IOException e) {
         e.printStackTrace();
     }
	}
	

}
