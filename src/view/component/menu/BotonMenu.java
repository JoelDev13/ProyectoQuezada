/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component.menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author luis-
 */
public class BotonMenu extends JButton {

    /**
     * Crea un nuevo boton de menu
     */
    public BotonMenu() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(new Color(182, 249, 201));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        setHorizontalAlignment(JButton.LEFT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    /**
     * Dependiendo del estado <i>seleted</i> del boton, se le pintara un hover blanco o no.
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        //System.out.println("LLAMDO A REPINTAR BLANCo");
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isSelected()) {
            g2.setColor(Color.white);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
        } else {
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 45, 45);
        }
        super.paintComponent(g);
    }
}
