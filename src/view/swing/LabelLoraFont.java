/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.swing;

import view.swing.whiteHollowButtom;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JLabel;

/**
 *
 * @author luis-
 */
public class LabelLoraFont extends JLabel {

    public LabelLoraFont() {
        // Añadimos una fuente especifica para los botones        
        InputStream fontImput = whiteHollowButtom.class.getResourceAsStream("/view/recursos/fuentes/lora.ttf");
        try {
            Font fuentePersonalizada = Font.createFont(Font.TRUETYPE_FONT, fontImput).deriveFont(Font.ITALIC, getFont().getSize());
            setFont(fuentePersonalizada);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo cargar la fuente.");
        }

    }

}
