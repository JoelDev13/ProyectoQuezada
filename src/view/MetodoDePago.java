/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.List;
import model.MetodoDePagoModel;
import model.dao.MetodoDePagoDao;
import javax.swing.DefaultListModel;

/**
 *
 * @author
 */
public class MetodoDePago extends javax.swing.JPanel {

    /**
     * Creates new form MetodoDePago
     */
    public MetodoDePago() {
        initComponents();
        cargarLista();
        // Hace que el campo ID sea de solo lectura
        jTextFieldId.setEditable(false);

        // Establecer el texto adecuado para el botón de editar
        roundedGreenButtomeditar.setText("Editar");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFondo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        roundedGreenButtomeditar = new view.RoundedGreenButtom();
        jLabelId = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabelServicio = new javax.swing.JLabel();
        jTextFieldServicio = new javax.swing.JTextField();
        roundedGreenButtomGuardar = new view.RoundedGreenButtom();
        roundedGreenButtom2 = new view.RoundedGreenButtom();

        jPanelFondo.setBackground(new java.awt.Color(0, 153, 0));

        jLabelTitulo.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabelTitulo.setText("Metodo De Pago");

        javax.swing.GroupLayout jPanelFondoLayout = new javax.swing.GroupLayout(jPanelFondo);
        jPanelFondo.setLayout(jPanelFondoLayout);
        jPanelFondoLayout.setHorizontalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFondoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFondoLayout.setVerticalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setToolTipText("");
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        roundedGreenButtomeditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-edit-30.png"))); // NOI18N
        roundedGreenButtomeditar.setText("Editar");
        roundedGreenButtomeditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedGreenButtomeditarActionPerformed(evt);
            }
        });

        jLabelId.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabelId.setText("ID:");

        jLabelServicio.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabelServicio.setText("Descripción:");

        roundedGreenButtomGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-save-30.png"))); // NOI18N
        roundedGreenButtomGuardar.setText("Guardar");
        roundedGreenButtomGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedGreenButtomGuardarActionPerformed(evt);
            }
        });

        roundedGreenButtom2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-delete-30 (1).png"))); // NOI18N
        roundedGreenButtom2.setText("Eliminar");
        roundedGreenButtom2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedGreenButtom2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelServicio)
                    .addComponent(jLabelId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldId)
                    .addComponent(jTextFieldServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundedGreenButtom2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedGreenButtomeditar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedGreenButtomGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {roundedGreenButtom2, roundedGreenButtomGuardar, roundedGreenButtomeditar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(roundedGreenButtomGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundedGreenButtomeditar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundedGreenButtom2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {roundedGreenButtom2, roundedGreenButtomGuardar, roundedGreenButtomeditar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(288, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 109, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        if (!evt.getValueIsAdjusting()) {
            // Obtiene la descripción seleccionada
            String descripcionSeleccionada = jList1.getSelectedValue();

            if (descripcionSeleccionada != null) {
                // Busca el método de pago por su descripción
                MetodoDePagoDao dao = new MetodoDePagoDao();
                MetodoDePagoModel metodo = dao.obtenerPorDescripcion(descripcionSeleccionada);

                if (metodo != null) {
                    // Muestra el ID y la descripción en los campos
                    jTextFieldId.setText(String.valueOf(metodo.getId()));
                    jTextFieldServicio.setText(metodo.getDescripcion());

                    // Deshabilitar edición cuando se selecciona un ítem
                    jTextFieldServicio.setEditable(false);
                    roundedGreenButtomeditar.setText("Editar");
                }
            }
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void roundedGreenButtomGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedGreenButtomGuardarActionPerformed
        String descripcion = jTextFieldServicio.getText().trim();
        String idText = jTextFieldId.getText().trim();

        controller.MetodoDePagoController controller = new controller.MetodoDePagoController();

        // Si hay un ID, es una actualización
        if (!idText.isEmpty()) {
            int id = Integer.parseInt(idText);
            if (controller.validarDescripcion(descripcion, id)) { // Usa la validación con ID
                boolean actualizado = controller.actualizarMetodo(id, descripcion);
                if (actualizado) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Método de pago actualizado correctamente.");
                    cargarLista(); // recarga la lista con los métodos actualizados
                    // Limpiar campos
                    jTextFieldId.setText("");
                    jTextFieldServicio.setText("");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar el método de pago.");
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "La descripción no es válida o ya existe.");
            }
        } else {
            // Si no hay ID, es un nuevo registro
            if (controller.validarDescripcion(descripcion)) {
                boolean guardado = controller.guardarMetodo(descripcion);
                if (guardado) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Método de pago guardado correctamente.");
                    cargarLista(); // recarga la lista con los métodos actualizados
                    jTextFieldServicio.setText(""); // limpia el campo
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al guardar el método de pago.");
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "La descripción no es válida o ya existe.");
            }
        }

        // Después de guardar o actualizar, resetear el formulario
        jTextFieldId.setText("");
        jTextFieldServicio.setText("");
        // Habilitar el campo para agregar nuevos registros
        jTextFieldServicio.setEditable(true);
        roundedGreenButtomeditar.setText("Editar");
    }//GEN-LAST:event_roundedGreenButtomGuardarActionPerformed

    private void roundedGreenButtom2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedGreenButtom2ActionPerformed
        String idText = jTextFieldId.getText();
        if (idText.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un método de pago para eliminar.");
            return;
        }
        int id = Integer.parseInt(idText);
        controller.MetodoDePagoController controller = new controller.MetodoDePagoController();
        boolean eliminado = controller.eliminarMetodo(id);

        if (eliminado) {
            javax.swing.JOptionPane.showMessageDialog(this, "Método de pago eliminado.");
            cargarLista();
            // Limpiar campos
            jTextFieldId.setText("");
            jTextFieldServicio.setText("");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar método de pago.");
        }

        // Después de eliminar, resetear el formulario
        jTextFieldId.setText("");
        jTextFieldServicio.setText("");
        // Habilitar el campo para agregar nuevos registros
        jTextFieldServicio.setEditable(true);
        roundedGreenButtomeditar.setText("Editar");
    }//GEN-LAST:event_roundedGreenButtom2ActionPerformed

    private void roundedGreenButtomeditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedGreenButtomeditarActionPerformed
        // Verifica si hay un ítem seleccionado
        if (jTextFieldId.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un método de pago para editar.");
            return;
        }

        // Hace editable el campo de texto del servicio
        jTextFieldServicio.setEditable(true);

        // Enfoca el campo de texto
        jTextFieldServicio.requestFocus();

        // Cambiar el texto del botón para indicar modo edición
        roundedGreenButtomeditar.setText("Editando...");
    }//GEN-LAST:event_roundedGreenButtomeditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelServicio;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldServicio;
    private view.RoundedGreenButtom roundedGreenButtom2;
    private view.RoundedGreenButtom roundedGreenButtomGuardar;
    private view.RoundedGreenButtom roundedGreenButtomeditar;
    // End of variables declaration//GEN-END:variables

    private void cargarLista() {
        MetodoDePagoDao dao = new MetodoDePagoDao();
        List<MetodoDePagoModel> metodos = dao.listarMetodos();

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (MetodoDePagoModel metodo : metodos) {
            // Solo añadimos la descripción a la lista
            modeloLista.addElement(metodo.getDescripcion());
        }

        jList1.setModel(modeloLista);
    }

}
