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

    // Agregar un Hover
    // probablemete este ligado al estado del boton (Selected o no selected)
    public BotonMenu() {

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(new Color(182, 249, 201));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        setHorizontalAlignment(JButton.LEFT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    
    @Override
    public void paintComponent(Graphics g) {
        System.out.println("LLAMDO A REPINTAR BLANCo");
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

    

//    @Override
//    protected void paintComponent(Graphics g) {
//        System.out.println("LLAMADO A REPINTAR ROJO");
//        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(getBackground());
//        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
//        super.paintComponent(g);
//    }

//
//    @Override
//    public void paint(Graphics g) {
//        
//        if (isSelected()) {
//            System.out.println("LLAMDO A REPINTAR BLANCo");
//            Graphics2D g2 = (Graphics2D) g.create();
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2.setColor(Color.white);
//            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
//        }
//        super.paint(g);
//    }

      
    
}
