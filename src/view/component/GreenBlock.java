/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author luis-
 */
public class GreenBlock extends javax.swing.JPanel {

 
    public GreenBlock() {
        initComponents();
        setOpaque(false); // Le avisamos a Swing que este componente no es opaco.
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Con este metodo pintamos el gradiente del panel. Se usa paintChildren para que se pinte en el
     * momento que se vayan a pintar los componentes hijos (los que estan dentro de este componente)
     * @param g objeto graphics
     */
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint grad = new GradientPaint(0, 0, Color.decode("#7dd181") , getWidth(), 0, Color.decode("#b6f9c9"));
        g2.setPaint(grad);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight()); // dibujamos un rectangulo 
       // g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 15, 15); 
        super.paintChildren(g);

    }


    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
