/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 * Esta clase crea botones con un hover de color verde en su parte izquierda
 * @author luis-
 */
public class whiteHollowButtom extends JButton {
    
    
    private boolean hovered;
    
    /**
     * Crea un boton con un hover de color verde en su parte izquierda.
     */
    public whiteHollowButtom() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 0, 3, 0));
        // Añadimos una fuente especifica para los botones        
        InputStream fontImput = whiteHollowButtom.class.getResourceAsStream("/view/recursos/fuentes/cinzel.ttf");
        try {
            Font fuentePersonalizada = Font.createFont(Font.TRUETYPE_FONT, fontImput).deriveFont(Font.BOLD, 12f);
            setFont(fuentePersonalizada);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo cargar la fuente.");
        }
        
        // En este objeto anonimo sobreescribimos los siguientes metodos
        // para que el boton consigua el hover de color verde
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Anthilashing para que nuestro futuro circulo sea suave.
       
        g2.setColor(Color.white);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // Dibujar la barra verde en hover
        if (hovered) {
            g2.setColor( Color.decode("#7dd181")); // Color verde personalizado
            g2.fillRect(0, 0, 10, getHeight()); // Barra verde a la izquierda
        }

        g2.dispose(); // Cerramos ese objeto graphics para ahorrar recursos.
        super.paintComponent(g);
    }
    
    
    
}
