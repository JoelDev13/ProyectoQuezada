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

/**
 * Esta clase crea objetos label que usen la fuenta QuattrocentoBold
 * @author luis-
 */
public class LabelQuattrocentoBold extends JLabel {

    public LabelQuattrocentoBold() {
        // Añadimos una fuente especifica para los botones        
        InputStream fontImput = whiteHollowButtom.class.getResourceAsStream("/view/recursos/fuentes/quattrocento-Bold.ttf");
        try {
            Font fuentePersonalizada = Font.createFont(Font.TRUETYPE_FONT, fontImput).deriveFont(Font.BOLD, getFont().getSize());
            setFont(fuentePersonalizada);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo cargar la fuente.");
        }

    }

}
