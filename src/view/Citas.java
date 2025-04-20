/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JComboBox;
import model.doctor.DoctorLigeroDTO;
import model.paciente.PacienteDTO;
import model.especialidad.Especialidad;
import model.servicios.Servicio;
import view.component.table.CustomTable;

/**
 * Vista de las Citas
 * @author luis-
 */
public class Citas extends javax.swing.JPanel {

    /**
     * Creates new form Citas
     */
    public Citas() {
        initComponents();
        customTable1.customizarScrollBar(jScrollPane1);

        this.deshabilitarCampos(); // Solamente se puede modificar los otros filtros
        this.ocultarIds();
        this.datePicker1.setCloseAfterSelected(true);
    }


    public void limpiarCampos() {
        this.txtPacienteCedula.setText("");
        this.txtPacienteNombre.setText("");
        this.txtDoctorNombre.setText("");
        this.datePicker1.clearSelectedDate();
        cbEspecialidad.setSelectedIndex(0);
        cbServicio.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
    }

    public void LimpiarPacienteSeleccionado() {
        this.txtPacienteCedula.setText("");
        this.txtPacienteNombre.setText("");
    }

    public void limpiarOtrosFiltros() {
        this.datePicker1.clearSelectedDate();
        cbEspecialidad.setSelectedIndex(0);
        cbServicio.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
    }


    /**
     * La tabla de citas tiene ocultas las ID de doctores, horarios, pacientes...
     * para que puedan ser recuperadas ala hora de actualizar los datos. Estos se ocultan.
     */
    private void ocultarIds() {
        for (int i = 1; i <= 5; i++) {
            this.customTable1.getColumnModel().getColumn(i).setMinWidth(0); // Ocultamos el IDDOC
            this.customTable1.getColumnModel().getColumn(i).setMaxWidth(0);
            this.customTable1.getColumnModel().getColumn(i).setWidth(0);
        }
    }
    
    /**
     * Recupera todos los datos proporcionados para filtrar citas que cumplan las descripcions.
     * Este metodo recibe (desde su controlador) los objetos que contienen al paciente y doctor seleccionado
     * para encapsular la logica de desempaque aqui.
     * 
     * Los parametros que no fueron entregados se convierten en null, para que la consulta no los considere,
     * de ahi que se usen objetos como Integer
     * @param paciente objeto <code>#PacienteDTO</code> que enviara el controllador
     * @param doctor objeto <code>#DoctorDTO</code> que enviara el controllador
     * @return Objeto <code>#Citas</code> con todos los ID para el filtrado.
     */
    public model.citas.Citas obtenerDatosParaFiltrado(PacienteDTO paciente, DoctorLigeroDTO doctor) {
        model.citas.Citas cita = new model.citas.Citas();

        Integer idPaciente = (paciente != null) ? paciente.getId() : null;
        Integer idDoctor = (doctor != null) ? doctor.getId() : null;

        Especialidad esp = (Especialidad) cbEspecialidad.getSelectedItem();
        Integer idEspecialidad = (esp != null) ? esp.getId() : null;

        Servicio serv = (Servicio) cbServicio.getSelectedItem();
        Integer idServicio = (serv != null) ? serv.getId() : null;

        cita.setIdPaciente(idPaciente);
        cita.setIdDoctor(idDoctor);
        cita.setIdEspecialidad(idEspecialidad);
        cita.setIdServicio(idServicio);

        String estado = ((String) cbEstado.getModel().getSelectedItem());
        estado = (estado.trim().isEmpty()) ? null : estado;

        cita.setEstado(estado);

        LocalDate[] fechas = datePicker1.getSelectedDateRange();
        if (fechas != null) {
            cita.setFechaInicioFiltro(fechas[0]);
            cita.setFechaFinFiltro(fechas[1]);
           
        } else {
            cita.setFechaInicioFiltro(null);
            cita.setFechaFinFiltro(null);
        }
        
        
         // No se filtra por horario del medico.
        return cita;
    }

    public void deshabilitarCampos() {
        txtPacienteCedula.setEditable(false);
        txtPacienteNombre.setEditable(false);
        txtDoctorNombre.setEditable(false);
    }

    /**
     * Muestra el nombre y cedula del paciente elegido desde el 
     * ElegirPacienteDialog
     * @param paciente 
     */
    public void mostrarPacienteElegido(PacienteDTO paciente) {
        if (paciente != null) {
            txtPacienteNombre.setText(paciente.getNombre());
            txtPacienteCedula.setText(paciente.getCedula());
        }
    }

    /**
     * Muestra el nombre del doctor elegido desde el 
     * ElegirDoctorDialog
     * @param doctor 
     */
    public void mostrarDoctorElegido(DoctorLigeroDTO doctor) {
        if (doctor != null) {
            txtDoctorNombre.setText(doctor.getNombre());
        }
    }
    
    // Getters de objetos de esta vista
    public JButton getBtnPacienteFiltrar() {
        return btnPacienteFiltrar;
    }

    public JButton getBtnDoctorFiltrar() {
        return btnDoctorFiltrar;
    }

    public JComboBox getCbServicios() {
        return cbServicio;
    }

    public JComboBox getCbEspecialidad() {
        return cbEspecialidad;
    }

    public CustomTable getCustomTable() {
        return customTable1;
    }

    public JButton getBtnCitasFiltrar() {
        return btnCitasFiltrar;
    }

    public JButton getBtnLimpiarFiltros() {
        return btnLimpiarFiltros;
    }

    public JButton getBtnEditarEstado() {
        return btnEditarEstado;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
    
    public JButton getBtnReAgendar() {
        return btnReagendar;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePicker1 = new raven.datetime.DatePicker();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlPacientes = new javax.swing.JPanel();
        txtPacienteNombre = new javax.swing.JTextField();
        txtPacienteCedula = new javax.swing.JTextField();
        labelPacienteNombre = new javax.swing.JLabel();
        lbPacienteCedula = new javax.swing.JLabel();
        btnPacienteFiltrar = new view.component.RoundedGreenButtom();
        pnlDoctor = new javax.swing.JPanel();
        lbDoctorNombre = new javax.swing.JLabel();
        txtDoctorNombre = new javax.swing.JTextField();
        btnDoctorFiltrar = new view.component.RoundedGreenButtom();
        pnlOtrosFiltros = new javax.swing.JPanel();
        lbEspecialidad = new javax.swing.JLabel();
        lbDesde = new javax.swing.JLabel();
        lblServicio = new javax.swing.JLabel();
        jtxtDatePicker = new javax.swing.JFormattedTextField();
        cbEspecialidad = new javax.swing.JComboBox<>();
        cbServicio = new javax.swing.JComboBox<>();
        lbEstado = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        pnlContendorBotones = new javax.swing.JPanel();
        btnLimpiarFiltros = new view.component.RoundedGreenButtom();
        btnCitasFiltrar = new view.component.RoundedGreenButtom();
        btnReagendar = new view.component.RoundedGreenButtom();
        btnCancelar = new view.component.RoundedGreenButtom();
        btnEditarEstado = new view.component.RoundedGreenButtom();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        customTable1 = new view.component.table.CustomTable();

        datePicker1.setDateSelectionMode(raven.datetime.DatePicker.DateSelectionMode.BETWEEN_DATE_SELECTED);
        datePicker1.setEditor(jtxtDatePicker);
        datePicker1.setSeparator(" hasta ");

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel1.setText("Citas");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        pnlPacientes.setBackground(new java.awt.Color(204, 255, 204));
        pnlPacientes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14))); // NOI18N

        labelPacienteNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelPacienteNombre.setText("Nombre:");

        lbPacienteCedula.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbPacienteCedula.setText("Cedula:");

        btnPacienteFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-search-30.png"))); // NOI18N

        javax.swing.GroupLayout pnlPacientesLayout = new javax.swing.GroupLayout(pnlPacientes);
        pnlPacientes.setLayout(pnlPacientesLayout);
        pnlPacientesLayout.setHorizontalGroup(
            pnlPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPacientesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPacienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPacienteCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPacienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPacienteCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnPacienteFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlPacientesLayout.setVerticalGroup(
            pnlPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPacientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPacienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPacienteNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPacienteCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPacienteCedula))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPacientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPacienteFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pnlDoctor.setBackground(new java.awt.Color(204, 255, 204));
        pnlDoctor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Doctor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14))); // NOI18N

        lbDoctorNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbDoctorNombre.setText("Nombre:");

        btnDoctorFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-search-30.png"))); // NOI18N

        javax.swing.GroupLayout pnlDoctorLayout = new javax.swing.GroupLayout(pnlDoctor);
        pnlDoctor.setLayout(pnlDoctorLayout);
        pnlDoctorLayout.setHorizontalGroup(
            pnlDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoctorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDoctorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(txtDoctorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoctorFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDoctorLayout.setVerticalGroup(
            pnlDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoctorLayout.createSequentialGroup()
                .addGroup(pnlDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDoctorLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDoctorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDoctorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDoctorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDoctorFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlOtrosFiltros.setBackground(new java.awt.Color(204, 255, 204));
        pnlOtrosFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Otros filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14))); // NOI18N

        lbEspecialidad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbEspecialidad.setText("Especialidad:");

        lbDesde.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbDesde.setText("Desde:");

        lblServicio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblServicio.setText("Servicio:");

        lbEstado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbEstado.setText("Estado:");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "PENDIENTE", "CANCELADA", "COMPLETADA" }));

        javax.swing.GroupLayout pnlOtrosFiltrosLayout = new javax.swing.GroupLayout(pnlOtrosFiltros);
        pnlOtrosFiltros.setLayout(pnlOtrosFiltrosLayout);
        pnlOtrosFiltrosLayout.setHorizontalGroup(
            pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOtrosFiltrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOtrosFiltrosLayout.createSequentialGroup()
                        .addGroup(pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbDesde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbEspecialidad, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(pnlOtrosFiltrosLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(lblServicio)))
                        .addGap(8, 8, 8)
                        .addGroup(pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtxtDatePicker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbEspecialidad, javax.swing.GroupLayout.Alignment.LEADING, 0, 171, Short.MAX_VALUE)
                                .addComponent(cbServicio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOtrosFiltrosLayout.createSequentialGroup()
                        .addComponent(lbEstado)
                        .addGap(8, 8, 8)
                        .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );
        pnlOtrosFiltrosLayout.setVerticalGroup(
            pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOtrosFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOtrosFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlOtrosFiltrosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbEspecialidad, jtxtDatePicker});

        pnlContendorBotones.setBackground(new java.awt.Color(204, 255, 204));

        btnLimpiarFiltros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-clear-30.png"))); // NOI18N
        btnLimpiarFiltros.setText("L.Filtros");

        btnCitasFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-search-30.png"))); // NOI18N
        btnCitasFiltrar.setText("Filtrar");
        btnCitasFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitasFiltrarActionPerformed(evt);
            }
        });

        btnReagendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-edit-calendar-30.png"))); // NOI18N
        btnReagendar.setText("Reagendar");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-cancel-30.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        btnEditarEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/pacientes/icons/icons8-edit-30.png"))); // NOI18N
        btnEditarEstado.setText("C. Estado");

        javax.swing.GroupLayout pnlContendorBotonesLayout = new javax.swing.GroupLayout(pnlContendorBotones);
        pnlContendorBotones.setLayout(pnlContendorBotonesLayout);
        pnlContendorBotonesLayout.setHorizontalGroup(
            pnlContendorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContendorBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContendorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContendorBotonesLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContendorBotonesLayout.createSequentialGroup()
                        .addGroup(pnlContendorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnCitasFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReagendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContendorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditarEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContendorBotonesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnCitasFiltrar, btnEditarEstado, btnLimpiarFiltros, btnReagendar});

        pnlContendorBotonesLayout.setVerticalGroup(
            pnlContendorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContendorBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContendorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCitasFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContendorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReagendar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContendorBotonesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnCitasFiltrar, btnEditarEstado, btnLimpiarFiltros, btnReagendar});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOtrosFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContendorBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pnlDoctor, pnlPacientes});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pnlOtrosFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pnlPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(pnlContendorBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jSeparator3.setForeground(new java.awt.Color(0, 153, 0));
        jSeparator3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N

        customTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "idPaciente", "idDoctor", "idServicio", "idEspecialidad", "idHorario", "FECHA", "ESTADO", "CEDULA", "PACIENTE", "DOCTOR", "SERVICIO", "ESPECIALIDAD", "HORARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(customTable1);
        if (customTable1.getColumnModel().getColumnCount() > 0) {
            customTable1.getColumnModel().getColumn(0).setResizable(false);
            customTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
            customTable1.getColumnModel().getColumn(1).setResizable(false);
            customTable1.getColumnModel().getColumn(2).setResizable(false);
            customTable1.getColumnModel().getColumn(3).setResizable(false);
            customTable1.getColumnModel().getColumn(4).setResizable(false);
            customTable1.getColumnModel().getColumn(5).setResizable(false);
            customTable1.getColumnModel().getColumn(6).setResizable(false);
            customTable1.getColumnModel().getColumn(6).setPreferredWidth(60);
            customTable1.getColumnModel().getColumn(7).setResizable(false);
            customTable1.getColumnModel().getColumn(7).setPreferredWidth(60);
            customTable1.getColumnModel().getColumn(8).setResizable(false);
            customTable1.getColumnModel().getColumn(8).setPreferredWidth(50);
            customTable1.getColumnModel().getColumn(9).setResizable(false);
            customTable1.getColumnModel().getColumn(9).setPreferredWidth(40);
            customTable1.getColumnModel().getColumn(10).setResizable(false);
            customTable1.getColumnModel().getColumn(10).setPreferredWidth(60);
            customTable1.getColumnModel().getColumn(11).setResizable(false);
            customTable1.getColumnModel().getColumn(11).setPreferredWidth(100);
            customTable1.getColumnModel().getColumn(12).setResizable(false);
            customTable1.getColumnModel().getColumn(13).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCitasFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitasFiltrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCitasFiltrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.RoundedGreenButtom btnCancelar;
    private view.component.RoundedGreenButtom btnCitasFiltrar;
    private view.component.RoundedGreenButtom btnDoctorFiltrar;
    private view.component.RoundedGreenButtom btnEditarEstado;
    private view.component.RoundedGreenButtom btnLimpiarFiltros;
    private view.component.RoundedGreenButtom btnPacienteFiltrar;
    private view.component.RoundedGreenButtom btnReagendar;
    private javax.swing.JComboBox<String> cbEspecialidad;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbServicio;
    private view.component.table.CustomTable customTable1;
    private raven.datetime.DatePicker datePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JFormattedTextField jtxtDatePicker;
    private javax.swing.JLabel labelPacienteNombre;
    private javax.swing.JLabel lbDesde;
    private javax.swing.JLabel lbDoctorNombre;
    private javax.swing.JLabel lbEspecialidad;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbPacienteCedula;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JPanel pnlContendorBotones;
    private javax.swing.JPanel pnlDoctor;
    private javax.swing.JPanel pnlOtrosFiltros;
    private javax.swing.JPanel pnlPacientes;
    private javax.swing.JTextField txtDoctorNombre;
    private javax.swing.JTextField txtPacienteCedula;
    private javax.swing.JTextField txtPacienteNombre;
    // End of variables declaration//GEN-END:variables
}
