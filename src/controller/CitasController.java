/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.citas.CitasDTO;
import model.dao.PacienteDao;
import model.dao.citas.CitasDao;
import model.dao.doctor.DoctorLigeroDAO;
import model.dao.especialidad.EspecialidadDao;
import model.dao.servicios.ServiciosDao;
import model.doctor.DoctorLigeroDTO;
import model.especialidad.Especialidad;
import model.paciente.PacienteDTO;
import model.servicios.Servicio;
import view.Citas;
import view.EditarEstadoCitaDialog;
import view.ElegirDoctorDialog;
import view.ElegirPacienteDialog;
import view.EditarFechaCitaDialog;

/**
 * Controllador de la vista Citas.
 * @author luis-
 */
public class CitasController implements ActionListener {

    /**
     * Paciente seleccinado por el usuario
     */
    protected PacienteDTO pacienteSeleccionado;
    /**
     * Doctor seleccionado por el usuario
     */
    protected DoctorLigeroDTO doctorSeleccionado;

    protected Citas citasView;
    protected CitasDao citasDao;
    protected ServiciosDao serviciosDao;
    protected EspecialidadDao especialidadDao;

    public CitasController(Citas citasView, CitasDao citasDao, ServiciosDao servicioDao, EspecialidadDao especialidadDao) {
        this.citasView = citasView;
        this.citasDao = citasDao;
        this.serviciosDao = servicioDao;
        this.especialidadDao = especialidadDao;
        
        // Se llenan los ComboBoxes de la vista.
        this.llenarCbEspecialidades(); 
        this.llenarCbServicios();

        this.citasView.getBtnPacienteFiltrar().addActionListener(this);
        this.citasView.getBtnDoctorFiltrar().addActionListener(this);
        this.citasView.getBtnCitasFiltrar().addActionListener(this);
        this.citasView.getBtnLimpiarFiltros().addActionListener(this);
        this.citasView.getBtnEditarEstado().addActionListener(this);
        this.citasView.getBtnReAgendar().addActionListener(this);
        this.citasView.getBtnCancelar().addActionListener(this);
        
        // Le agregamos un listener a la tabla de la vista para que entre en modo de pre-edicion
        // cuando se haga click en una fila especifica
        this.citasView.getCustomTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                entrarEstadoPreEdicion();
            }
        });
        
        this.entrarEstadoNormal();
        this.listarTodos();
    }
    
    /**
     * Llena la tabla de la vista Citas con el List&lt;CitasDTO&gt; entregado
     * @param citas objeto List&lt;CitasDTO&gt; de donde se sacara la informacion
     */
    protected void llenarTabla(List<CitasDTO> citas) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        DefaultTableModel modelo = (DefaultTableModel) citasView.getCustomTable().getModel();
        modelo.setRowCount(0);
        for (CitasDTO cita : citas) {
            Object[] fila = new Object[14];
            fila[0] = cita.getId();
            fila[1] = cita.getIdPaciente();
            fila[2] = cita.getIdDoc();
            fila[3] = cita.getIdServicio();
            fila[4] = cita.getIdEspecialidad();
            fila[5] = cita.getIdHorario();

            fila[6] = cita.getFecha().format(formatoFecha);
            fila[7] = cita.getEstado();
            fila[8] = cita.getCedulaPaciente();
            fila[9] = cita.getNombrePaciente();
            fila[10] = cita.getNombreDoctor();
            fila[11] = cita.getServicio();
            fila[12] = cita.getEspecialidad();
            fila[13] = (cita.getHoraInicio().format(formatoHora) + " - " + cita.getHoraFin().format(formatoHora));
            modelo.addRow(fila);
        }
    }
    
    /**
     * Lista todas las citas agendadas
     */
    protected void listarTodos() {
        try {
            model.citas.Citas cita = new model.citas.Citas(); // objeto vacio para que filtre todos;
            this.llenarTabla(citasDao.filtrarCitas(cita));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(citasView, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * LLena el ComboBox de especialidades con Objetos <code>Especialidad</code>
     */
    protected void llenarCbEspecialidades() {
        try {
            DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
            modelo.addElement(null);
            List<Especialidad> especialidades = especialidadDao.listarEspecialidades();
            for (Especialidad esp : especialidades) {
                modelo.addElement(esp);
            }
            citasView.getCbEspecialidad().setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(citasView, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * LLena el ComboBox de servicios con Objetos <code>Servicio</code>
     */
    protected void llenarCbServicios() {
        try {
            DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
            modelo.addElement(null); // para tener una seleeccion que sea "ningun servicio"
            List<Servicio> servicios = serviciosDao.listarServicios();
            for (Servicio servicio : servicios) {
                modelo.addElement(servicio);
            }
            citasView.getCbServicios().setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(citasView, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Limpia los filtros de la vista Cita
     */
    protected void limpiarFiltros() {
        pacienteSeleccionado = null;
        doctorSeleccionado = null;
        citasView.limpiarCampos();
    }

    /**
     * Si se hace click en una fila cuyo estado es PENDIENTE, se permite la reagendacion
     * de la cita que representa dicha fila. Solamente se puede reagendar desde el dia actual
     *  y en un dia en el que el medico labore (que posea horarios en dicho dia. El metodo valida que la
     * cita no sea nula para ecitar NullPointer errors.
     */
    protected void editarFechaCitaSeleccionada() {
        model.citas.Citas citaOriginal = this.ObtenerCitaSelecciona();
        if (citaOriginal != null) {
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(citasView);
            DoctorLigeroDAO doctorDao = new DoctorLigeroDAO();

            EditarFechaCitaDialog dialog = new EditarFechaCitaDialog(ventanaPadre, true, doctorDao, citaOriginal);
            dialog.setLocationRelativeTo(ventanaPadre);
            dialog.setVisible(true);

            model.citas.Citas actualizacion = dialog.ObtenerDatosDeActualizacionCita();
            if (actualizacion != null) {
                try {
                    citasDao.actualizarCita(actualizacion);
                    this.listarTodos();
                    JOptionPane.showMessageDialog(ventanaPadre, "Cita actualizada correctamente", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(citasView, e.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
    }

    /**
     * Si se hace click en una fila cuyo estado sea PENDIENTE, permite cambiar el estado de la cita
     * que representa dicha fila. Los estados pueden ser PENDIENTE, CANCELADA o COMPLETADA.
     * Las secretarias solamente pueden cambiar el estado de una cita a CANCELADA y es una accion irrreversible.
     * 
     * El Dialog editarCitaDialog recibe en un modelo <Code>Cita</code> los datos de la cita original, y dentro del dialog
     * modifica el estao de la cita. actualizacion de la cita es devuelta como un objeto cita, que mas tarde se entrega
     * al DAO de citas para actualizar dicha cita.
     * 
     */
    protected void editarEstadoCitaSeleccionada() {
        model.citas.Citas citaOriginal = this.ObtenerCitaSelecciona();
        if (citaOriginal != null) {
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(citasView);
            EditarEstadoCitaDialog dialog = new EditarEstadoCitaDialog(ventanaPadre, true, citaOriginal, false);
            dialog.setLocationRelativeTo(ventanaPadre);
            dialog.setVisible(true);

            model.citas.Citas actualizacion = dialog.ObtenerDatosDeActualizacion(); // se reciben las modificaciones hechas por el usuario

            // Es posible que el usuario cierre la ventana en vez de seleccionar. por ello
            // validamos que el campo no sea nulo.
            if (actualizacion != null) {
                try {
                    citasDao.actualizarCita(actualizacion);
                    this.listarTodos();
                    JOptionPane.showMessageDialog(ventanaPadre, "Cita actualizada correctamente", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(citasView, e.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
                    e.printStackTrace();
                }
            }

        }
    }
    
    /**
     * Una vez seleccionada una fila, se entra en el estado de pre-edicion, permitiendole
     * al usuario solamente reagendar o cambiar el estado de una cita. Se puede usar el boton
     * cancelar para salir de dicho modo.
     * 
     */
    protected void entrarEstadoPreEdicion() {
        this.citasView.getBtnCitasFiltrar().setEnabled(false);
        this.citasView.getBtnLimpiarFiltros().setEnabled(false);
        this.citasView.getBtnEditarEstado().setEnabled(true);
        this.citasView.getBtnCancelar().setEnabled(true);
        this.citasView.getBtnReAgendar().setEnabled(true);
    }
    
    /**
     * Vuelve al estado normal del view. Solamente permite filtrar y borrar los filtros
     */
    protected void entrarEstadoNormal() {
        this.citasView.getBtnCitasFiltrar().setEnabled(true);
        this.citasView.getBtnLimpiarFiltros().setEnabled(true);
        this.citasView.getBtnEditarEstado().setEnabled(false);
        this.citasView.getBtnCancelar().setEnabled(false);
        this.citasView.getBtnReAgendar().setEnabled(false);
    }
    
    /**
     * Recupera la informacion de una fila de la tabla de la vista Citas en un objeto modelo Citas siempre
     * y cuando la cita seleccionada este PENDIENTE
     * @return <code>Citas</code>
     */
    protected model.citas.Citas ObtenerCitaSelecciona() {
        model.citas.Citas citaOriginal = null;
        int fila = this.citasView.getCustomTable().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(citasView, "Elija una cita primero", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String estado = (String) citasView.getCustomTable().getValueAt(fila, 7);

        if ("COMPLETADA".equals(estado) || "CANCELADA".equals(estado)) {
            JOptionPane.showMessageDialog(citasView, "Solamente puede editar/Reagendar citas pendientes", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        citaOriginal = new model.citas.Citas();

        // Estas columnas estan ocultas
        citaOriginal.setId((int) this.citasView.getCustomTable().getValueAt(fila, 0)); // ID de la cita
        citaOriginal.setIdPaciente((int) this.citasView.getCustomTable().getValueAt(fila, 1));
        citaOriginal.setIdDoctor((int) this.citasView.getCustomTable().getValueAt(fila, 2));
        citaOriginal.setIdServicio((int) this.citasView.getCustomTable().getValueAt(fila, 3));
        citaOriginal.setIdEspecialidad((int) this.citasView.getCustomTable().getValueAt(fila, 4));
        citaOriginal.setIdHorarioDoctor((int) this.citasView.getCustomTable().getValueAt(fila, 5));

        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = (String) this.citasView.getCustomTable().getValueAt(fila, 6); // fecha;
        LocalDate fechaDeLaCita = LocalDate.parse(fecha, formatoEntrada);

        citaOriginal.setFecha(fechaDeLaCita);
        citaOriginal.setEstado(((String) this.citasView.getCustomTable().getValueAt(fila, 7)));

        return citaOriginal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Filtrar pacientes mediante el dialog ElegirPacienteDialog
        if (e.getSource() == citasView.getBtnPacienteFiltrar()) {

            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(citasView);

            ElegirPacienteDialog dialog = new ElegirPacienteDialog(ventanaPadre, true);
            PacienteDao pDAO = new PacienteDao();
            ElegirPacienteDialogController dialogController = new ElegirPacienteDialogController(dialog, pDAO);

            dialog.setLocationRelativeTo(ventanaPadre);
            dialog.setVisible(true);

            pacienteSeleccionado = dialogController.obtenerPaciente();
            citasView.mostrarPacienteElegido(pacienteSeleccionado);

        // Filtrar doctores mediante el dialog ElegirDoctorDialog
        } else if (e.getSource() == citasView.getBtnDoctorFiltrar()) {
            System.out.println("DESDE EL BOTON DOCTOR FILTRAR");
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(citasView);

            ElegirDoctorDialog dialogView = new ElegirDoctorDialog(ventanaPadre, true);
            DoctorLigeroDAO dDAO = new DoctorLigeroDAO();
            EspecialidadDao eDAO = new EspecialidadDao();
            ElegirDoctorDialogController dialogController = new ElegirDoctorDialogController(dialogView, dDAO, eDAO);

            dialogView.setLocationRelativeTo(ventanaPadre);
            dialogView.setVisible(true);

            doctorSeleccionado = dialogController.obtenerDoctorSeleccionado();
            citasView.mostrarDoctorElegido(doctorSeleccionado);

         // filtra las citas que cumplan con las descripciones seleccionadas
        } else if (e.getSource() == citasView.getBtnCitasFiltrar()) {
            model.citas.Citas filtros = citasView.obtenerDatosParaFiltrado(pacienteSeleccionado, doctorSeleccionado);

            System.out.println("DENTRO DEL BOTON DE FILTRAR");
            try {
                this.llenarTabla(citasDao.filtrarCitas(filtros));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(citasView, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
            }

        // limpiar los filtros
        } else if (e.getSource() == citasView.getBtnLimpiarFiltros()) {
            this.limpiarFiltros();

        // Reagenda una cita
        } else if (e.getSource() == citasView.getBtnReAgendar()) {
            this.editarFechaCitaSeleccionada();

            
        // Edita el estado de una cita
        } else if (e.getSource() == citasView.getBtnEditarEstado()) {
            this.editarEstadoCitaSeleccionada();
            
        // Vuelve la vista al modo normal (es decir, sale del modo de Pre-edicion)
        } else if (e.getSource() == citasView.getBtnCancelar()) {
            this.entrarEstadoNormal();
        }
    }

}
