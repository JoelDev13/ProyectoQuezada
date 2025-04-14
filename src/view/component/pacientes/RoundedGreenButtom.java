/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component.pacientes;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;

/**
 *
 * @author luis-
 */
public class RoundedGreenButtom extends JButton{

    public RoundedGreenButtom() {
        putClientProperty("JButton.buttonType", "roundRect");
        putClientProperty("text", "bold");
        setBackground(new Color(126, 209, 129));
        
    }
    
    
}
