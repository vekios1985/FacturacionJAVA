package org.facturacion.principal.utils;


import javax.swing.*;
import javax.swing.table.TableModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

public class GenerarPdf {
	
	 public static void main(String[] args) {
	        // Crear ventana con un JTable de ejemplo
	        JFrame frame = new JFrame("Exportar JTable a PDF");
	        JTable table = new JTable(
	            new Object[][]{
	                {"1", "Juan", "25"},
	                {"2", "María", "30"},
	                {"3", "Luis", "22"}
	            },
	            new Object[]{"ID", "Nombre", "Edad"}
	        );
	        JScrollPane scrollPane = new JScrollPane(table);
	        JButton exportButton = new JButton("Exportar a PDF");

	        exportButton.addActionListener(e -> exportarJTableAPDF(table));
	        
	        frame.add(scrollPane, "Center");
	        frame.add(exportButton, "South");
	        frame.setSize(400, 300);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	    }

	    private static void exportarJTableAPDF(JTable table) {
	        // Abrir JFileChooser para seleccionar la ubicación
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Guardar como...");
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        fileChooser.setSelectedFile(new File("tabla.pdf")); // Nombre predeterminado
	        int userSelection = fileChooser.showSaveDialog(null);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File fileToSave = fileChooser.getSelectedFile();
	            if (!fileToSave.getName().endsWith(".pdf")) {
	                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
	            }
	            
	            try (PDDocument document = new PDDocument()) {
	                PDPage page = new PDPage();
	                document.addPage(page);

	                PDPageContentStream contentStream = new PDPageContentStream(document, page);

	                contentStream.setFont(PDType1Font.HELVETICA, 12);
	                contentStream.beginText();
	                contentStream.setLeading(14.5f);
	                contentStream.newLineAtOffset(50, 750);

	                // Obtener los datos del JTable
	                TableModel model = table.getModel();
	                // Escribir encabezados
	                for (int i = 0; i < model.getColumnCount(); i++) {
	                    contentStream.showText(model.getColumnName(i) + "    ");
	                }
	                contentStream.newLine();

	                // Escribir las filas
	                for (int row = 0; row < model.getRowCount(); row++) {
	                    for (int col = 0; col < model.getColumnCount(); col++) {
	                        contentStream.showText(model.getValueAt(row, col).toString() + "    ");
	                    }
	                    contentStream.newLine();
	                }

	                contentStream.endText();
	                contentStream.close();

	                document.save(fileToSave);
	                JOptionPane.showMessageDialog(null, "Archivo PDF guardado en: " + fileToSave.getAbsolutePath());
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());
	            }
	        }
	    }
}
