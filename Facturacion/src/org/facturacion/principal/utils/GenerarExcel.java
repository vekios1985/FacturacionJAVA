package org.facturacion.principal.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenerarExcel {
	
	public static void CrearDocumento(String []columnas,Object[][]datos) {
	
	 Workbook workbook = new XSSFWorkbook();

 
     Sheet sheet = workbook.createSheet("Hoja de Datos");

    
     Row headerRow = sheet.createRow(0);
     

     
     int i=0;
     for(String s:columnas)
     {
    	 headerRow.createCell(i).setCellValue(s);
    	 i++;
     }


     int rowNum = 1;
     for (Object[] dato : datos) {
         Row row = sheet.createRow(rowNum++);
         for(int l=0;l<columnas.length;l++)
         row.createCell(l).setCellValue(dato[l].toString());
     }

     for (int j = 0; j < columnas.length; j++) {
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
