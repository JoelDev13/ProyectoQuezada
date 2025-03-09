/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component.menu;

import java.awt.Color;
import java.awt.Cursor;
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
        setBorder(new EmptyBorder(10, 10, 10, 10));

        setHorizontalAlignment(JButton.LEFT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }
    
    
}
