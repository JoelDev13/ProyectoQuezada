/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import javax.swing.JTextField;
import view.swing.LoginButtom;

/**
 * Panel del login
 * @author luis-
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Login() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0)); // hacemos invisible este form
        
        txtUser.requestFocusInWindow(); // Hacemos que el txtUser Tenga el foco desde el inicio.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPanel1 = new view.component.login.LayeredPanel();
        greenBlock1 = new view.component.login.GreenBlock();
        labelCenzilFont1 = new view.swing.LabelCenzilFont();
        labelQuattrocentoBold1 = new view.swing.LabelQuattrocentoBold();
        labelLoraFont1 = new view.swing.LabelLoraFont();
        contentBlock1 = new view.component.login.ContentBlock();
        ButtomsPanel = new javax.swing.JPanel();
        LogoLabel = new javax.swing.JLabel();
        BtnContactanos = new view.swing.whiteHollowButtom();
        BtnLogIn = new view.swing.whiteHollowButtom();
        BtonCerrarVentana = new javax.swing.JButton();
        loginPanel = new javax.swing.JPanel();
        labelLogin = new view.swing.LabelCenzilFont();
        txtPassword = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        btnLogin = new view.swing.LoginButtom();

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

        BtnContactanos.setText("contactanos");
        BtnContactanos.setFocusPainted(false);

        BtnLogIn.setText("Log in");
        BtnLogIn.setFocusPainted(false);

        BtonCerrarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/images/on-off-button (1).png"))); // NOI18N
        BtonCerrarVentana.setBorderPainted(false);
        BtonCerrarVentana.setContentAreaFilled(false);
        BtonCerrarVentana.setFocusPainted(false);
        BtonCerrarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtonCerrarVentanaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtomsPanelLayout = new javax.swing.GroupLayout(ButtomsPanel);
        ButtomsPanel.setLayout(ButtomsPanelLayout);
        ButtomsPanelLayout.setHorizontalGroup(
            ButtomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnContactanos, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(BtnLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ButtomsPanelLayout.createSequentialGroup()
                .addGroup(ButtomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ButtomsPanelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(LogoLabel))
                    .addGroup(ButtomsPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(BtonCerrarVentana)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ButtomsPanelLayout.setVerticalGroup(
            ButtomsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtomsPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LogoLabel)
                .addGap(47, 47, 47)
                .addComponent(BtnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(BtnContactanos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtonCerrarVentana)
                .addContainerGap())
        );

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));
        loginPanel.setPreferredSize(new java.awt.Dimension(280, 0));

        labelLogin.setText("Iniciar sesion");
        labelLogin.setFont(labelLogin.getFont().deriveFont(labelLogin.getFont().getSize()+6f));

        txtPassword.setDoubleBuffered(true);
        txtPassword.setPreferredSize(new java.awt.Dimension(70, 20));

        txtUser.setDoubleBuffered(true);
        txtUser.setNextFocusableComponent(txtPassword);
        txtUser.setPreferredSize(new java.awt.Dimension(70, 20));

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/recursos/images/right.png"))); // NOI18N
        btnLogin.setFocusPainted(false);
        btnLogin.setPreferredSize(new java.awt.Dimension(35, 38));

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    
    // usado para agregar la funcionalidad en el LoginControlador
    public LoginButtom getBtnLogin() {
        return btnLogin;
    }
    
    
    private void BtonCerrarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtonCerrarVentanaActionPerformed
       this.dispose(); // cerramos la ventana.
    }//GEN-LAST:event_BtonCerrarVentanaActionPerformed
    
    public JTextField getTxtPasword() {
        return txtPassword;
    }

 

    public JTextField getTxtUser() {
        return txtUser;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.swing.whiteHollowButtom BtnContactanos;
    private view.swing.whiteHollowButtom BtnLogIn;
    private javax.swing.JButton BtonCerrarVentana;
    private javax.swing.JPanel ButtomsPanel;
    private javax.swing.JLabel LogoLabel;
    private view.swing.LoginButtom btnLogin;
    private view.component.login.ContentBlock contentBlock1;
    private view.component.login.GreenBlock greenBlock1;
    private view.swing.LabelCenzilFont labelCenzilFont1;
    private view.swing.LabelCenzilFont labelLogin;
    private view.swing.LabelLoraFont labelLoraFont1;
    private view.swing.LabelQuattrocentoBold labelQuattrocentoBold1;
    private view.component.login.LayeredPanel layeredPanel1;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
    

}
