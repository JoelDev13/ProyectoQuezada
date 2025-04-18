/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.dao.doctor.DoctorLigeroDAO;
import model.horario.Horario;
import utilidades.TraductorDias;

/**
 * Crea un dialogo para poder editar / actualizar la fecha
 * de una cita
 * @author luis-
 */
public class EditarFechaCitaDialog extends javax.swing.JDialog {

    private DoctorLigeroDAO doctorDao;
    private model.citas.Citas citaOriginal;
    /**
     * El proposito de eta flag es validar si el usuario
     * confirmo la actualización, para decidir si enviar null
     * o un objeto al controllador que llamo ese Dialog
     */
    private boolean Confirmacion = false;
    /**
     * Usado para decidir cuales dias son seleccionables en el DatePicker
     */
    private EnumMap<DayOfWeek, Boolean> diasHabiles;

    /**
     * Crea un nuevo dialogo para cambiar la fecha de una cita.
     * 
     * @param parent vista padre de este objeto
     * @param modal No dejara editar otra ventana sies <code>true</code>
     * @param doctorDao Dao para acceder a la db
     * @param citaOriginal Citaoriginal que se va a actualizar
     */
    public EditarFechaCitaDialog(java.awt.Frame parent, boolean modal, DoctorLigeroDAO doctorDao, model.citas.Citas citaOriginal) {
        super(parent, modal);
        this.doctorDao = doctorDao;
        this.citaOriginal = citaOriginal;
        initComponents();
        this.Convertir(obtenerDiasHabiles());
        this.HabilitarDiasSeleccionables();

        this.btnActualizar.setEnabled(false);
        this.datePicker.addDateSelectionListener((dateSelectionEvent) -> {
            btnActualizar.setEnabled(true);
            this.llenarCbHorarios(datePicker.getSelectedDate());
        });

        this.btnActualizar.addActionListener((e) -> {
            if (datePicker.getSelectedDate() == null) {
                JOptionPane.showMessageDialog(parent, "Tienes seleccionar una fecha para actualizar la cita !", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                this.Confirmacion = true;
                this.dispose();
            }
        });
    }

    /**
     * obtiene los dias laborales del medico entregado en el constructor.
     * Los dias laborables son los dias de la semana en los que el doctor tiene horarios
     *
     * @return
     */
    private List<String> obtenerDiasHabiles() {

        List<String> dias = null;
        try {
            dias = doctorDao.diasHabiles(citaOriginal.getIdDoctor());
        } catch (SQLException ex) {
            Logger.getLogger(EditarFechaCitaDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dias;
    }
    
    /**
     * Convierte los dias recuperados como String a un EnumMap de DayOfWeek , Boolean.
     * Este map es usado mas tarde en #HabilitarDiasSeleccionados.
     *
     * @param diasObtenidos
     */
    private void Convertir(List<String> diasObtenidos) {
        Set<DayOfWeek> diasActivos = new HashSet<>();

        for (String dia : diasObtenidos) {
            DayOfWeek dayOfWeek = TraductorDias.aDayOfWeek(dia); // Obtenemos el equivalente de ese dia en DayOfWeek
            diasActivos.add(dayOfWeek);
        }

        // Creamos un Enum con todos los dias de la semana y un bolean asociado.
        // usamos el atributo de la clase;
        this.diasHabiles = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek dia : DayOfWeek.values()) {
            this.diasHabiles.put(dia, diasActivos.contains(dia));
        }

    }
    

    /**
     * Este metodo solamente permite elegir dias a partir de hoy y en los que el doctor labore (que posea un horario);
     */
    private void HabilitarDiasSeleccionables() {
        datePicker.setDateSelectionAble((LocalDate date) -> {
            if (date.isAfter(LocalDate.now())) {
                return diasHabiles.get(date.getDayOfWeek());
            }
            return false;
        });
    }

     
    private void llenarCbHorarios(LocalDate fecha) {
        try {
            String dia = TraductorDias.aString(fecha.getDayOfWeek());

            DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
            List<Horario> horarios = doctorDao.ObtenerHorariosDeUnDia(citaOriginal.getIdDoctor(), dia);
            for (Horario horario : horarios) {
                modelo.addElement(horario);
            }
            cbHorarios.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *Este metodo es la forma unica formade como recuperar la información actualizada.
     * Este es llamado luego de que que el dialogo se cierre (dispose)s
     *
     * @return objeto <code>Cita</code> cita con los cambios hechos. Retorna Null si el usuario cerro la ventana
     */
    public model.citas.Citas ObtenerDatosDeActualizacionCita() {
        model.citas.Citas actualizacion = new model.citas.Citas();
        
        if (datePicker.getSelectedDate() == null || this.Confirmacion == false) {
            return null;
        }
        
        Horario horarioSeleccionado = (Horario) cbHorarios.getSelectedItem();
       
        actualizacion.setId(citaOriginal.getId());
        actualizacion.setIdPaciente(citaOriginal.getIdPaciente());
        actualizacion.setIdDoctor(citaOriginal.getIdDoctor());
        actualizacion.setIdServicio(citaOriginal.getIdServicio());
        actualizacion.setIdEspecialidad(citaOriginal.getIdEspecialidad());
        actualizacion.setEstado(citaOriginal.getEstado());
        actualizacion.setFecha(datePicker.getSelectedDate());
        actualizacion.setIdHorarioDoctor(horarioSeleccionado.getId());
        return actualizacion;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenido = new javax.swing.JPanel();
        ftxtDatePicker = new javax.swing.JFormattedTextField();
        lbEstado = new javax.swing.JLabel();
        datePicker = new raven.datetime.DatePicker();
        lbFecha = new javax.swing.JLabel();
        cbHorarios = new javax.swing.JComboBox<>();
        btnActualizar = new view.component.RoundedGreenButtom();
        lbTitulo = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlContenido.setBackground(new java.awt.Color(204, 255, 204));

        ftxtDatePicker.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        lbEstado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbEstado.setText("horarios:");
        lbEstado.setPreferredSize(new java.awt.Dimension(62, 30));

        datePicker.setEditor(ftxtDatePicker);

        lbFecha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbFecha.setText("Fecha:");

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-uninstalling-updates-30.png"))); // NOI18N
        btnActualizar.setText("Actualizar");

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addComponent(lbFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxtDatePicker))
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlContenidoLayout.createSequentialGroup()
                                .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbHorarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ftxtDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnlContenidoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ftxtDatePicker, lbFecha});

        pnlContenidoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbHorarios, lbEstado});

        lbTitulo.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Reagendar");

        jSeparator3.setForeground(new java.awt.Color(0, 153, 0));
        jSeparator3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N

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
                .addComponent(pnlContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.RoundedGreenButtom btnActualizar;
    private javax.swing.JComboBox<Object> cbHorarios;
    private raven.datetime.DatePicker datePicker;
    private javax.swing.JFormattedTextField ftxtDatePicker;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlContenido;
    // End of variables declaration//GEN-END:variables
}
