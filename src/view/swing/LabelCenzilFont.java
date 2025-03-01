/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.swing;

import view.swing.whiteHollowButtom;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author luis-
 */
public class LabelCenzilFont extends JLabel {

    public LabelCenzilFont() {
        // Añadimos una fuente especifica para los botones        
        InputStream fontImput = whiteHollowButtom.class.getResourceAsStream("/view/recursos/fuentes/cinzel.ttf");
        try {
            Font fuentePersonalizada = Font.createFont(Font.TRUETYPE_FONT, fontImput).deriveFont(Font.BOLD, getFont().getSize());
            setFont(fuentePersonalizada);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo cargar la fuente.");
        }
        

    }
    
    public void centrarEsteComponente() {
        SwingUtilities.invokeLater(() -> { // Para evitar que me explote el programa. Que se centre la GUI cuando todo este cargado
            Container parentContainer = this.getParent();
            int parentWidth = parentContainer.getWidth();
            int parentHeight = parentContainer.getHeight();
            int childWidth = this.getWidth();
            int childHeight = this.getHeight();
            int newX = (parentWidth - childWidth) / 2;
            int newY = (parentHeight - childHeight) / 2;
            this.setBounds(newX, newY, childWidth, childHeight);
        });
    }

}
