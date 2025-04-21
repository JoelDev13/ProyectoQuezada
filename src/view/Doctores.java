/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import model.doctor.Doctor;
import model.especialidad.Especialidad;
import view.component.RoundedGreenButtom;

/**
 *
 * @author luis-
 */
public class Doctores extends javax.swing.JPanel {

    /**
     * Creates new form FormModelo
     */
    public Doctores() {
        initComponents();
    
    }


public String getTextoFiltro() {
    return jTxtFiltros1.getText().trim();
}

public Especialidad getEspecialidadSeleccionada() {
    Especialidad selected = (Especialidad) jCmbFiltro2.getSelectedItem();
    return (selected != null) ? selected : new Especialidad();
}

public void actualizarTabla(List<Doctor> doctores) {
    DefaultTableModel model = (DefaultTableModel) customTable1.getModel();
    model.setRowCount(0); // Limpiar la tabla
    
    for (Doctor doctor : doctores) {
        model.addRow(new Object[]{
            doctor.getId(),
            doctor.getUsuario(),
            doctor.getNombre(),
            doctor.getApellido(),
            doctor.getEmail(),
            doctor.getTelefono()
        });
    }
}

// Métodos para acceder a los componentes
public javax.swing.JButton getBtnFiltrar() {
    return btnFiltrar1;
}

public javax.swing.JTextField getTxtFiltro() {
    return jTxtFiltros1;
}

public JComboBox<Object> getComboEspecialidad() {
    return jCmbFiltro2;
}
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jTxtFiltros1 = new javax.swing.JTextField();
        jCmbFiltro2 = new javax.swing.JComboBox<>();
        lbEspecialidad = new javax.swing.JLabel();
        lbEspecialidad1 = new javax.swing.JLabel();
        btnFiltrar1 = new view.component.RoundedGreenButtom();
        btnHorarios = new view.component.RoundedGreenButtom();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        customTable1 = new view.component.table.CustomTable();

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("REGISTRAR");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton9.setBackground(new java.awt.Color(0, 153, 102));
        jButton9.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jButton9.setText("Pediatría");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jFormattedTextField1.setText("jFormattedTextField1");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jTxtFiltros1.setBorder(null);
        jTxtFiltros1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFiltros1ActionPerformed(evt);
            }
        });

        jCmbFiltro2.setBorder(null);
        jCmbFiltro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbFiltro2ActionPerformed(evt);
            }
        });

        lbEspecialidad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbEspecialidad.setText("Filtros:");

        lbEspecialidad1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbEspecialidad1.setText("Especialidad:");

        btnFiltrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-search-30.png"))); // NOI18N
        btnFiltrar1.setText("Filtrar");

        btnHorarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-edit-calendar-30.png"))); // NOI18N
        btnHorarios.setText("HORARIOS");
        btnHorarios.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbEspecialidad)
                    .addComponent(lbEspecialidad1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCmbFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFiltros1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFiltrar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHorarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCmbFiltro2, jTxtFiltros1});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFiltrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFiltros1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbEspecialidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCmbFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jCmbFiltro2, jTxtFiltros1});

        jSeparator2.setForeground(new java.awt.Color(0, 153, 0));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel2.setText("Doctores");

        customTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "USUARIO", "NOMBRE", "APELLIDO", "EMAIL", "TELEFONO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(customTable1);
        if (customTable1.getColumnModel().getColumnCount() > 0) {
            customTable1.getColumnModel().getColumn(0).setResizable(false);
            customTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            customTable1.getColumnModel().getColumn(1).setResizable(false);
            customTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            customTable1.getColumnModel().getColumn(2).setResizable(false);
            customTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            customTable1.getColumnModel().getColumn(3).setResizable(false);
            customTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
            customTable1.getColumnModel().getColumn(4).setResizable(false);
            customTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
            customTable1.getColumnModel().getColumn(5).setResizable(false);
            customTable1.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtFiltros1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFiltros1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFiltros1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jCmbFiltro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbFiltro2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCmbFiltro2ActionPerformed

public RoundedGreenButtom getBtnFiltrar1() {
    return btnFiltrar1; // Asegúrate de que btnFiltrar1 sea de tipo RoundedGreenButtom
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.RoundedGreenButtom btnFiltrar1;
    private view.component.RoundedGreenButtom btnHorarios;
    private view.component.table.CustomTable customTable1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<Object> jCmbFiltro2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTxtFiltros1;
    private javax.swing.JLabel lbEspecialidad;
    private javax.swing.JLabel lbEspecialidad1;
    // End of variables declaration//GEN-END:variables

   

 
}
