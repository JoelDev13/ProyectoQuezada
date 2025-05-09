/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import model.HistorialPagoModel;
import model.MetodoDePagoModel;
import model.dao.MetodoDePagoDao;
import model.servicios.Servicio;

/**
 * Pequeño dialog que se encarga de devolver el metodo de pago de una cita. Tambien funciona como confirmación de dicha cita.
 *
 * @author luis-
 */
public class PagarUnaCitaDialog extends javax.swing.JDialog {

    /**
     * Creates new form PagarUnaCitaDialog
     */
    private MetodoDePagoDao metodoDePagoDao;
    
    /**
     * Confirma que el usuario le haya dado al boton de pagar la cita
     * para que proceda correctamente.
     */
    private boolean Confirmacion = false;

    public PagarUnaCitaDialog(java.awt.Frame parent, boolean modal, double monto, MetodoDePagoDao metodoDao) {
        super(parent, modal);

        this.metodoDePagoDao = metodoDao;

        initComponents();
        this.llenarMonto(monto);
        this.llenarMetodosDePagos();
        this.agregarFuncionalidadAlBoton();
        this.txtMonto.setEditable(false);
    }

    private void llenarMonto(double monto) {
        this.txtMonto.setText(String.valueOf(monto));
    }

    private void llenarMetodosDePagos() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (MetodoDePagoModel m : this.metodoDePagoDao.listarMetodos()) {
            modelo.addElement(m);
        }
        this.cbMetodoDePago.setModel(modelo);
    }

    private void agregarFuncionalidadAlBoton() {
        this.btnPagarCita.addActionListener((e) -> {
            this.Confirmacion = true;
            this.dispose();
        });
    }

    public Integer obtenerMetodoDePago() {
        if (this.Confirmacion) {
           return ((MetodoDePagoModel) this.cbMetodoDePago.getSelectedItem()).getId();
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbMetodoDePago = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtMonto = new javax.swing.JTextField();
        lbMonto = new javax.swing.JLabel();
        cbMetodoDePago = new javax.swing.JComboBox<>();
        btnPagarCita = new view.component.RoundedGreenButtom();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        lbMetodoDePago.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lbMetodoDePago.setText("Metodo de pago:");

        jSeparator1.setForeground(new java.awt.Color(0, 153, 0));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N

        txtMonto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtMonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lbMonto.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        lbMonto.setText("Monto:");

        btnPagarCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/menu/icons/pagos.png"))); // NOI18N
        btnPagarCita.setText("Pagar la cita");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lbMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbMetodoDePago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbMetodoDePago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(btnPagarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbMetodoDePago)
                    .addComponent(lbMetodoDePago, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btnPagarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.RoundedGreenButtom btnPagarCita;
    private javax.swing.JComboBox<Object> cbMetodoDePago;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbMetodoDePago;
    private javax.swing.JLabel lbMonto;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
