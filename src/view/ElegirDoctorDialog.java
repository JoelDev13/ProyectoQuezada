/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import model.doctor.DoctorLigeroDTO;
import net.miginfocom.swing.MigLayout;

/**
 * Esta clase, junto a ElegirDoctorDialogController, se encarga de filtrar,
 * seleccionar y recuperar un objeto DoctorDto.
 * 
 * @author luis-
 */
public class ElegirDoctorDialog extends javax.swing.JDialog {

    /**
     * Creates new form ElegirDoctorDialog
     */
    public ElegirDoctorDialog(java.awt.Frame parent, boolean modal) {
        super(parent, "Elige un doctor", modal);
        initComponents();
        panelMIG.setLayout(new MigLayout("wrap,insets 10, gap 10", "[230]10px[230]", "[]10px[]"));

        customizarScrollBar();
    }
    
    /**
     * Este metodo es quien recupera el objeto <code>DoctorDto</code>. Es llamado despues
     * de que este dialogo se haya cerrado (dispose)
     * @return <code>DoctorDto</code> con la informacion del doctor seleccionado.
     */
    public DoctorLigeroDTO obtenerDatos() {
        String nombre = (txtNombre.getText().trim().isEmpty()) ? null : txtNombre.getText().trim();
        String apellido = (txtApellido.getText().trim().isEmpty()) ? null : txtApellido.getText().trim();
        
        String especialidad = (String) cbEspecialidad.getModel().getSelectedItem();
        especialidad = (especialidad.trim().isEmpty()) ? null : especialidad;
        
        DoctorLigeroDTO d = new DoctorLigeroDTO();
        d.setNombre(nombre);
        d.setApellido(apellido);
        d.setEspecialidad(especialidad);
        return d;

    }
    
    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        cbEspecialidad.setSelectedItem(0);
    }
    
    
    private void customizarScrollBar() {
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "thumbArc: 999;"
                + "trackInsets: 3,3,3,3;"
                + "thumbInsets: 3,3,3,3;"
                + "background:$Table.background;");
    }
    
    
    
    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }
    
    public JPanel getPanelMig() {
        return panelMIG;
    }
    
    public JComboBox getCbEspecialidad() {
        return cbEspecialidad;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFiltrado = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        lbApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lbEspecialidad = new javax.swing.JLabel();
        cbEspecialidad = new javax.swing.JComboBox<>();
        pnlBotones = new javax.swing.JPanel();
        btnFiltrar = new view.component.RoundedGreenButtom();
        scroll = new javax.swing.JScrollPane();
        panelMIG = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlFiltrado.setBackground(new java.awt.Color(204, 255, 204));

        labelNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNombre.setText("Nombre:");

        lbApellido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbApellido.setText("Apellidos:");

        lbEspecialidad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbEspecialidad.setText("Especialidad:");

        pnlBotones.setBackground(new java.awt.Color(204, 255, 204));

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-search-30.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFiltradoLayout = new javax.swing.GroupLayout(pnlFiltrado);
        pnlFiltrado.setLayout(pnlFiltradoLayout);
        pnlFiltradoLayout.setHorizontalGroup(
            pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltradoLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEspecialidad))
                .addGap(10, 10, 10)
                .addGroup(pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtApellido)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlFiltradoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelNombre, lbApellido});

        pnlFiltradoLayout.setVerticalGroup(
            pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltradoLayout.createSequentialGroup()
                .addGroup(pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFiltradoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbApellido)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFiltradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlFiltradoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelNombre, lbApellido});

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelMIG.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout panelMIGLayout = new javax.swing.GroupLayout(panelMIG);
        panelMIG.setLayout(panelMIGLayout);
        panelMIGLayout.setHorizontalGroup(
            panelMIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );
        panelMIGLayout.setVerticalGroup(
            panelMIGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        scroll.setViewportView(panelMIG);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addComponent(pnlFiltrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.RoundedGreenButtom btnFiltrar;
    private javax.swing.JComboBox<String> cbEspecialidad;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel lbApellido;
    private javax.swing.JLabel lbEspecialidad;
    private javax.swing.JPanel panelMIG;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlFiltrado;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
