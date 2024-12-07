package org.facturacion.principal.utils;


import javax.swing.*;
import javax.swing.table.TableModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerarPdf {
	

	    public static void exportarJTableAPDF(JTable table) {
	       

	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Guardar como...");
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        fileChooser.setSelectedFile(new File("tabla_apaisada.pdf"));
	        int userSelection = fileChooser.showSaveDialog(null);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File fileToSave = fileChooser.getSelectedFile();
	            if (!fileToSave.getName().endsWith(".pdf")) {
	                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
	            }

	            try (PDDocument document = new PDDocument()) {
	                PDRectangle landscape = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());
	                PDPage page = new PDPage(landscape);
	                document.addPage(page);

	                PDPageContentStream contentStream = new PDPageContentStream(document, page);

	                float margin = 50;
	                float yStart = page.getMediaBox().getHeight() - margin;

	                // Añadir logotipo
	                PDImageXObject image = PDImageXObject.createFromFile("resources/images/logotipo.png", document);
	                float imageWidth = 100;
	                float imageHeight = 50;
	                contentStream.drawImage(image, margin, yStart - imageHeight, imageWidth, imageHeight);
	                yStart -= imageHeight + 20;

	                // Añadir título centrado
	                String titulo = "Reporte de Datos (Apaisado)";
	                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
	                float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(titulo) / 1000 * 18;
	                float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2;
	                contentStream.beginText();
	                contentStream.newLineAtOffset(titleX, yStart);
	                contentStream.showText(titulo);
	                contentStream.endText();
	                yStart -= 30;

	                // Configurar tabla
	                TableModel model = table.getModel();
	                int numCols = model.getColumnCount();
	                int numRows = model.getRowCount();
	                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
	                float rowHeight = 20f;
	                float cellMargin = 5f;

	                float[] columnWidths = new float[numCols];
	                for (int i = 0; i < numCols; i++) {
	                    columnWidths[i] = tableWidth / numCols;
	                }

	                // Dibujar encabezados
	                for (int col = 0; col < numCols; col++) {
	                    float xPosition = margin + col * columnWidths[col];
	                    contentStream.beginText();
	                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	                    contentStream.newLineAtOffset(xPosition + cellMargin, yStart - rowHeight / 2);
	                    contentStream.showText(model.getColumnName(col));
	                    contentStream.endText();
	                }
	                yStart -= rowHeight;

	                // Dibujar filas
	                for (int row = 0; row < numRows; row++) {
	                    float maxHeightInRow = rowHeight;
	                    for (int col = 0; col < numCols; col++) {
	                        String cellText = model.getValueAt(row, col).toString();
	                        float xPosition = margin + col * columnWidths[col];

	                        // Dividir texto en líneas
	                        List<String> lines = splitTextToFitWidth(cellText, columnWidths[col] - 2 * cellMargin, PDType1Font.HELVETICA, 12);
	                        maxHeightInRow = Math.max(maxHeightInRow, lines.size() * rowHeight);

	                        // Dibujar texto línea por línea
	                        float textY = yStart - rowHeight / 2;
	                        for (String line : lines) {
	                            contentStream.beginText();
	                            contentStream.setFont(PDType1Font.HELVETICA, 12);
	                            contentStream.newLineAtOffset(xPosition + cellMargin, textY);
	                            contentStream.showText(line);
	                            contentStream.endText();
	                            textY -= rowHeight;
	                        }
	                    }
	                    yStart -= maxHeightInRow;
	                }

	                contentStream.close();
	                document.save(fileToSave);

	                JOptionPane.showMessageDialog(null, "Archivo PDF guardado en: " + fileToSave.getAbsolutePath());
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());
	            }
	            
	        }
	        
	    }

	    
	    private static List<String> splitTextToFitWidth(String text, float width, PDType1Font font, int fontSize) {
	        List<String> lines = new ArrayList<>();
	        StringBuilder line = new StringBuilder();

	        String[] words = text.split(" ");
	        for (String word : words) {
	            // Verificar si la palabra contiene '@'
	            if (word.contains("@")) {
	                String[] splitAt = word.split("@", 2);
	                for (int i = 0; i < splitAt.length; i++) {
	                    String part = splitAt[i];
	                    // Agregar el separador '@' solo si no es el último fragmento
	                    if (i > 0) {
	                        part = "@" + part;
	                    }

	                    float lineWidth = 0;
	                    try {
	                        lineWidth = font.getStringWidth(line + part + " ") / 1000 * fontSize;
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }

	                    if (lineWidth > width) {
	                        lines.add(line.toString().trim());
	                        line = new StringBuilder(part + " ");
	                    } else {
	                        line.append(part).append(" ");
	                    }
	                }
	            } else {
	                float lineWidth = 0;
	                try {
	                    lineWidth = font.getStringWidth(line + word + " ") / 1000 * fontSize;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }

	                if (lineWidth > width) {
	                    lines.add(line.toString().trim());
	                    line = new StringBuilder(word + " ");
	                } else {
	                    line.append(word).append(" ");
	                }
	            }
	        }

	        if (!line.isEmpty()) {
	            lines.add(line.toString().trim());
	        }

	        return lines;
	    }

}
