/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author luis-
 */
public class EditarEstadoCitaDialog extends javax.swing.JDialog {

    private model.citas.Citas citaOriginal;
    private model.citas.Citas actualizacion;

    /**
     * Creates new form EditarEstadoCitaDialog
     */
    public EditarEstadoCitaDialog(java.awt.Frame parent, boolean modal, model.citas.Citas citaOriginal, boolean esDoctor) {
        super(parent, modal);

        initComponents();
        this.citaOriginal = citaOriginal;
        this.llenarModelo(esDoctor);
        this.agregarFuncionalidad(this.btnActualizar); // esto es para tener la implementacion en otro lugar;s
        
        btnActualizar.setEnabled(false);
        cbEstado.addActionListener((e) -> {
            if (cbEstado.getSelectedIndex() == 0) {
                btnActualizar.setEnabled(false);
            } else {
                btnActualizar.setEnabled(true);
            }
        });
    }

    private void llenarModelo(boolean esDoctor) {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel<>(new String[]{"PENDIENTE", "CANCELADA"});

        if (esDoctor) {
            modelo = new DefaultComboBoxModel<>(new String[]{"PENDIENTE", "COMPLETADA"});
        }
        cbEstado.setModel(modelo);
    }

    private void agregarFuncionalidad(JButton btnActualizar) {
        btnActualizar.addActionListener((e) -> {
            
            if (cbEstado.getSelectedIndex() == 1) {
                int respuesta = JOptionPane.showConfirmDialog(
                        this,
                        "¿Estas seguro que quieres cambiar el estado de la cita? \n"
                        + " Esta accion no se puede revertir",
                        "Confirmacion",
                        JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.YES_OPTION) {
                    this.actualizacion = new model.citas.Citas();
                    this.actualizacion.setId(citaOriginal.getId());
                    this.actualizacion.setIdPaciente(citaOriginal.getIdPaciente());
                    this.actualizacion.setIdDoctor(citaOriginal.getIdDoctor());
                    this.actualizacion.setIdServicio(citaOriginal.getIdServicio());
                    this.actualizacion.setIdEspecialidad(citaOriginal.getIdEspecialidad());
                    this.actualizacion.setIdHorarioDoctor(citaOriginal.getIdHorarioDoctor());
                    this.actualizacion.setFecha(citaOriginal.getFecha());
                    this.actualizacion.setEstado((String) cbEstado.getSelectedItem());
                    this.dispose();
                }
            }
        });

    }

    public model.citas.Citas ObtenerDatosDeActualizacion() {
        return this.actualizacion;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        pnlContenido = new javax.swing.JPanel();
        lbEstado = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        btnActualizar = new view.component.RoundedGreenButtom();
        lbTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jSeparator3.setForeground(new java.awt.Color(0, 153, 0));
        jSeparator3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N

        pnlContenido.setBackground(new java.awt.Color(204, 255, 204));

        lbEstado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbEstado.setText("estado:");
        lbEstado.setPreferredSize(new java.awt.Dimension(62, 30));

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-uninstalling-updates-30.png"))); // NOI18N
        btnActualizar.setText("Actualizar");

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pnlContenidoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbEstado, lbEstado});

        lbTitulo.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Cambiar Estado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.RoundedGreenButtom btnActualizar;
    private javax.swing.JComboBox<Object> cbEstado;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlContenido;
    // End of variables declaration//GEN-END:variables
}
