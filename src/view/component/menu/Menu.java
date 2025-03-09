/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.component.menu;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import view.component.menu.event.EventMenu;

/**
 *
 * @author luis-
 */
public class Menu extends javax.swing.JPanel {



    private EventMenu event; // Evento para poder cambiar los paneles

    /**
     * Creates new form Menu
     * Este menu utiliza MigLayOut para funcionar. Instala la version 5.3 de la 
     * libreria por favor.
     */
    public Menu() {
        initComponents();
        setOpaque(false);
        panelMenuMIG.setLayout(new MigLayout("wrap, fillx, insets 3", "[fill]", "[]0[]"));
        
        // Debuggin Logs
        System.out.println(panelPerfil.getWidth());
        //System.out.printf("Panel Botones: %d , %d \n ", panelBotones.getWidth(), panelBotones.getHeight());
        SwingUtilities.invokeLater(() -> {
                    System.out.printf("Panel Perfil: %d, %d \n", panelPerfil.getWidth(), panelPerfil.getHeight());
                    System.out.printf("Panel Botones: %d, %d \n", panelBotones.getWidth(), panelBotones.getHeight());
        });
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotones = new view.component.RoundPanel();
        panelMenuMIG = new javax.swing.JPanel();
        panelPerfil = new view.component.RoundPanel();
        imageAvatar1 = new view.component.menu.ImageAvatar();
        labelCorreo = new javax.swing.JLabel();
        LabelNombre = new javax.swing.JLabel();
        labelRol = new javax.swing.JLabel();

        panelBotones.setBackground(new java.awt.Color(182, 249, 201));

        panelMenuMIG.setBackground(new java.awt.Color(182, 249, 201));

        javax.swing.GroupLayout panelMenuMIGLayout = new javax.swing.GroupLayout(panelMenuMIG);
        panelMenuMIG.setLayout(panelMenuMIGLayout);
        panelMenuMIGLayout.setHorizontalGroup(
            panelMenuMIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelMenuMIGLayout.setVerticalGroup(
            panelMenuMIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMenuMIG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMenuMIG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelPerfil.setBackground(new java.awt.Color(67, 152, 117));

        imageAvatar1.setForeground(new java.awt.Color(94, 189, 153));
        imageAvatar1.setBorderSize(5);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/avatar/p2.png"))); // NOI18N

        labelCorreo.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        labelCorreo.setForeground(new java.awt.Color(255, 255, 255));
        labelCorreo.setText("Correo");

        LabelNombre.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        LabelNombre.setForeground(new java.awt.Color(255, 255, 255));
        LabelNombre.setText("Nombre");

        labelRol.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        labelRol.setForeground(new java.awt.Color(255, 255, 255));
        labelRol.setText("Rol");

        javax.swing.GroupLayout panelPerfilLayout = new javax.swing.GroupLayout(panelPerfil);
        panelPerfil.setLayout(panelPerfilLayout);
        panelPerfilLayout.setHorizontalGroup(
            panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPerfilLayout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelCorreo)
                        .addGroup(panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPerfilLayout.createSequentialGroup()
                                .addComponent(LabelNombre)
                                .addGap(80, 80, 80))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPerfilLayout.createSequentialGroup()
                                .addComponent(labelRol)
                                .addGap(93, 93, 93))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPerfilLayout.createSequentialGroup()
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        panelPerfilLayout.setVerticalGroup(
            panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPerfilLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelNombre)
                .addGap(0, 0, 0)
                .addComponent(labelCorreo)
                .addGap(0, 0, 0)
                .addComponent(labelRol)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    public void initSecretaria(EventMenu event) {
        this.event = event;
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Pacientes", Paneles.GESTOR_DE_PACIENTES);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Agendar una cita", Paneles.AGENDAR_CITAS);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Gestor de citas", Paneles.GESTOR_DE_CITAS);
        agregarLogOff();
    }

    public void initDoctor(EventMenu event) {
        this.event = event;
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Pacientes", Paneles.GESTOR_DE_PACIENTES_DOC);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Agenda", Paneles.AGENDA_DOC);
        agregarLogOff();
        
    }

    public void initAdmin(EventMenu event) {
        this.event = event;
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Pacientes", Paneles.GESTOR_DE_PACIENTES);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Agendar una cita", Paneles.AGENDAR_CITAS);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Gestor de citas", Paneles.GESTOR_DE_CITAS);
      
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Agenda", Paneles. AGENDA_DOC);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Gestor de Usuarios", Paneles.GESTOR_DE_USUARIOS);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Doctores", Paneles.GESTOR_DE_DOCTORES);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Turnos", Paneles.GESTOR_DE_TURNOS);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Servicios", Paneles.GESTOR_DE_SERVICIOS);
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "Pagos", Paneles.PAGOS);
        agregarLogOff();
    }

    private void agregarLogOff() {
        agregarVacio();
        agregarMenu(new ImageIcon(getClass().getResource("/view/component/menu/icons/calendar (1).png")), "log out", Paneles.LOG_OFF);
        
    }

    /**
     * Agrega un nuevo boton de panel al menu del Dashboard.
     *
     * @param icon icono que aparece en el costado izquierdo
     * @param texto texto que aparece ala derecha del icono
     * @param indice numero que identifica exclusivamente a a este boton.
     */
    private void agregarMenu(Icon icon, String texto, Paneles panel) {
        BotonMenu b = new BotonMenu();
        b.setIcon(icon);
        b.setText(" " + texto);
        panelMenuMIG.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.seleccionado(panel); // envia el endicie del boton tocado por el user
                botonSeleccionado(b);
            }

        });

    }

    private void agregarVacio() {
        panelMenuMIG.add(new JLabel(), "Push");
    }

    /**
     * Este metodo le da el estado de seleccionado al boton que toco el usuario.
     * 
     * @param btnMenu
     */
    private void botonSeleccionado(BotonMenu btnMenu) {
        for (Component comp : panelMenuMIG.getComponents()) {
            if (comp instanceof BotonMenu) {
                BotonMenu b = (BotonMenu) comp;
                b.setSelected(false);
                System.out.println("El boton ha sido cambiado !");

            }
            btnMenu.setSelected(true);
        }
    }
    
    
    /**
     * Se ha sobreescrito el metodo con tal de darle la apariencia
     *  "Rectangular" del lado derecho del menu.
     * @param g 
     */
    
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // pintantdo el gap que hay entre panelPerfil y panelBotones
        g2.setColor(panelPerfil.getBackground());
        g2.fillRect(0, panelPerfil.getHeight() - 15, panelPerfil.getWidth(), 25);

        // pintar el costado derecho.
        g2.fillRect(panelPerfil.getWidth() - 10, 0, panelPerfil.getWidth(), panelPerfil.getHeight());

        g2.setColor(panelBotones.getBackground());
        g2.fillRect(panelBotones.getWidth() - 10, panelPerfil.getHeight() + 25, panelBotones.getWidth(), panelBotones.getHeight());

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelNombre;
    private view.component.menu.ImageAvatar imageAvatar1;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelRol;
    private view.component.RoundPanel panelBotones;
    private javax.swing.JPanel panelMenuMIG;
    private view.component.RoundPanel panelPerfil;
    // End of variables declaration//GEN-END:variables
}
