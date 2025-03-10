/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;

/**
 *
 * @author luis-
 * Logín View. Este es el panel de login del programa.
 * 
 * Este Logín contiene un Layered panel en el que se organizaran y se sobrepondran otros contenedores
 * que haran que de esa perspectiva de "3D"
 * 
 */
public class Login extends javax.swing.JFrame {

    // Recordemos que en las propiedades de este form esta marcado
    // el checkbox de undercoated (esto quita los bordes de la ventana y los botones de arriba de una ventana)
    public Login() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));  // hacemos el background transparente
        
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPanel1 = new view.component.LayeredPanel();
        greenBlock1 = new view.component.GreenBlock();
        labelCenzilFont1 = new view.swing.LabelCenzilFont();
        labelQuattrocentoBold1 = new view.swing.LabelQuattrocentoBold();
        labelLoraFont1 = new view.swing.LabelLoraFont();
        contentBlock1 = new view.component.ContentBlock();
        ButtomsPanel = new javax.swing.JPanel();
        LogoLabel = new javax.swing.JLabel();
        whiteHollowButtom1 = new view.swing.whiteHollowButtom();
        whiteHollowButtom2 = new view.swing.whiteHollowButtom();
        loginPanel = new javax.swing.JPanel();
        labelCenzilFont2 = new view.swing.LabelCenzilFont();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        greenRoundArrowButtoms1 = new view.swing.LoginButtom();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        layeredPanel1.setLayout(null);

        labelCenzilFont1.setText("Welcome to ");
        labelCenzilFont1.setFont(labelCenzilFont1.getFont().deriveFont(labelCenzilFont1.getFont().getSize()+3f));

        labelQuattrocentoBold1.setText("SOFT NOVA");
        labelQuattrocentoBold1.setFont(labelQuattrocentoBold1.getFont().deriveFont((float)40));

        labelLoraFont1.setText("<html>\nTu salud, tu tiempo, tu espacio. Con nuestra plataforma, agenda y gestiona tus citas médicas de manera rápida y segura.\n</html>");
        labelLoraFont1.setFont(labelLoraFont1.getFont().deriveFont((labelLoraFont1.getFont().getStyle() | java.awt.Font.ITALIC), 18));

        javax.swing.GroupLayout greenBlock1Layout = new javax.swing.GroupLayout(greenBlock1);
        greenBlock1.setLayout(greenBlock1Layout);
        greenBlock1Layout.setHorizontalGroup(
            greenBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(greenBlock1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(greenBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelQuattrocentoBold1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCenzilFont1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLoraFont1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        greenBlock1Layout.setVerticalGroup(
            greenBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(greenBlock1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelCenzilFont1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelQuattrocentoBold1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLoraFont1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        layeredPanel1.add(greenBlock1);
        greenBlock1.setBounds(220, 55, 400, 440);

        ButtomsPanel.setBackground(new java.awt.Color(255, 255, 255));
        ButtomsPanel.setPreferredSize(new java.awt.Dimension(120, 400));

        LogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/images/logo2_thumbnail_50x50.png"))); // NOI18N

        whiteHollowButtom1.setText("contactanos");
        whiteHollowButtom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteHollowButtom1ActionPerformed(evt);
            }
        });

        whiteHollowButtom2.setText("Log in");
        whiteHollowButtom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteHollowButtom2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtomsPanelLayout = new javax.swing.GroupLayout(ButtomsPanel);
        ButtomsPanel.setLayout(ButtomsPanelLayout);
        ButtomsPanelLayout.setHorizontalGroup(
            ButtomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(whiteHollowButtom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(whiteHollowButtom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ButtomsPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(LogoLabel)
                .addGap(35, 35, 35))
        );
        ButtomsPanelLayout.setVerticalGroup(
            ButtomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtomsPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LogoLabel)
                .addGap(47, 47, 47)
                .addComponent(whiteHollowButtom2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(whiteHollowButtom1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));
        loginPanel.setPreferredSize(new java.awt.Dimension(280, 0));

        labelCenzilFont2.setText("Iniciar sesion");
        labelCenzilFont2.setFont(labelCenzilFont2.getFont().deriveFont(labelCenzilFont2.getFont().getSize()+6f));

        jTextField1.setText("Contraseña");
        jTextField1.setDoubleBuffered(true);
        jTextField1.setPreferredSize(new java.awt.Dimension(70, 20));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setText("Usuario");
        jTextField2.setDoubleBuffered(true);
        jTextField2.setPreferredSize(new java.awt.Dimension(70, 20));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        greenRoundArrowButtoms1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/images/right.png"))); // NOI18N
        greenRoundArrowButtoms1.setPreferredSize(new java.awt.Dimension(35, 38));

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(greenRoundArrowButtoms1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                        .addComponent(labelCenzilFont2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(labelCenzilFont2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(greenRoundArrowButtoms1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contentBlock1Layout = new javax.swing.GroupLayout(contentBlock1);
        contentBlock1.setLayout(contentBlock1Layout);
        contentBlock1Layout.setHorizontalGroup(
            contentBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentBlock1Layout.createSequentialGroup()
                .addComponent(ButtomsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        contentBlock1Layout.setVerticalGroup(
            contentBlock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ButtomsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        layeredPanel1.add(contentBlock1);
        contentBlock1.setBounds(100, 75, 800, 400);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void whiteHollowButtom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteHollowButtom1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_whiteHollowButtom1ActionPerformed

    private void whiteHollowButtom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteHollowButtom2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_whiteHollowButtom2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButtomsPanel;
    private javax.swing.JLabel LogoLabel;
    private view.component.ContentBlock contentBlock1;
    private view.component.GreenBlock greenBlock1;
    private view.swing.LoginButtom greenRoundArrowButtoms1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private view.swing.LabelCenzilFont labelCenzilFont1;
    private view.swing.LabelCenzilFont labelCenzilFont2;
    private view.swing.LabelLoraFont labelLoraFont1;
    private view.swing.LabelQuattrocentoBold labelQuattrocentoBold1;
    private view.component.LayeredPanel layeredPanel1;
    private javax.swing.JPanel loginPanel;
    private view.swing.whiteHollowButtom whiteHollowButtom1;
    private view.swing.whiteHollowButtom whiteHollowButtom2;
    // End of variables declaration//GEN-END:variables
}
