/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingUtilities;
import model.citas.Citas;
import model.dao.MetodoDePagoDao;
import model.dao.PacienteDao;
import model.dao.citas.CitasDao;
import model.dao.doctor.DoctorLigeroDAO;
import model.dao.especialidad.EspecialidadDao;
import model.dao.servicios.ServiciosDao;
import model.doctor.DoctorLigeroDTO;
import model.especialidad.Especialidad;
import model.horario.Horario;
import model.paciente.PacienteDTO;
import model.servicios.Servicio;
import utilidades.TraductorDias;
import view.AgendarUnaCita;
import view.EditarFechaCitaDialog;
import view.ElegirDoctorDialog;
import view.ElegirPacienteDialog;
import view.PagarUnaCitaDialog;

/**
 * Controllador de la vista AgendarUnaCita
 * @author luis-
 */
public class AgendarUnaCitaController implements ActionListener {

    /**
     * Paciente seleccionado por el usuario.
     */
    private PacienteDTO pacienteSeleccionado;
    /**
     * Doctor seleccioando por el usuario
     */
    private DoctorLigeroDTO doctorSeleccionado;

    private ServiciosDao serviciosDao;
    private EspecialidadDao especialidadDao;
    private AgendarUnaCita view;
    private DoctorLigeroDAO doctorDao;
    private CitasDao citasDao;

    public AgendarUnaCitaController(AgendarUnaCita view, CitasDao citasDao, EspecialidadDao especialidadDao, ServiciosDao serviciosDao, DoctorLigeroDAO doctorDao) {
        this.view = view;
        this.citasDao = citasDao;
        this.serviciosDao = serviciosDao;
        this.especialidadDao = especialidadDao;
        this.doctorDao = doctorDao;

        this.view.getBtnPacienteFiltrar().addActionListener(this);
        this.view.getBtnDoctorFiltrar().addActionListener(this);
        this.view.getBtnAgendarUnaCita().addActionListener(this);
        this.view.getBtnLimpiarFiltros().addActionListener(this);

        /* 
            Se le agrega funcionalidad al ComboBox cbEspecialidad, con tal
            de que siempre actualize el ComboBox de servicios cuando se elija una
            especialidad. Se verifica que no sea null primero debido a la posibilidad
            de que se limpie los campos y desencadene el evento, generando una excepcion
            NullPointer.
        */
        this.view.getCbEspecialidad().addActionListener((e) -> {
            if (this.view.getCbEspecialidad().getSelectedItem() != null) {
                Especialidad especialidadElegida = (Especialidad) this.view.getCbEspecialidad().getSelectedItem();
                this.llenarCbServicios(especialidadElegida.getId());
            }
        });

        this.view.desactivarDatePicker();
        
        // Cuando se elija una fecha del calendario, se llenara el ComboBox de horarios
        // con los horarios de ese dia del medico.
        this.view.getDatePicker().addDateSelectionListener((dateSelectionEvent) -> {
            this.llenarCbHorarios(this.view.getDatePicker().getSelectedDate());
        });

        // le añadimos un elemento nulo a todos los ComboBoxes, con tal de evitar erroes
        // de null Exeption.
        DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
        modelo.addElement(null);
        this.view.getCbHorarios().setModel(modelo);
        this.view.getCbServicios().setModel(modelo);
        this.view.getCbServicios().setModel(modelo);
        this.llenarCbEspecialidades();
    }
    
    
    /**
     * Llena el comboBox de especialidades con las especialiades registrdas en la db
     */
    private void llenarCbEspecialidades() {
        try {
            DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
            modelo.addElement(null);
            List<Especialidad> especialidades = especialidadDao.listarEspecialidades();
            for (Especialidad esp : especialidades) {
                modelo.addElement(esp);
            }
            this.view.getCbEspecialidad().setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.view, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * LLena el ComboBox de servicios con Objetos <code>Servicio</code>
     */
    private void llenarCbServicios(int idEspecialidad) {

        try {
            DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
            modelo.addElement(null); // para tener una seleeccion que sea "ningun servicio"
            List<Servicio> servicios = serviciosDao.listarServiciosDeUnaEspecialidad(idEspecialidad);
            for (Servicio servicio : servicios) {
                modelo.addElement(servicio);
            }
            this.view.getCbServicios().setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.view, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
        }

    }

    /**
     * Llena el ComboBox de horarios con los horarios del medico de ese dia.
     * @param fecha 
     */
    private void llenarCbHorarios(LocalDate fecha) {
        if (fecha != null) {
            try {
                String dia = TraductorDias.aString(fecha.getDayOfWeek());
                DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
                modelo.addElement(null);
                List<Horario> horarios = doctorDao.ObtenerHorariosDeUnDia(this.doctorSeleccionado.getId(), dia);
                for (Horario horario : horarios) {
                    modelo.addElement(horario);
                }
                this.view.getCbHorarios().setModel(modelo);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * obtiene los dias laborales del medico entregado en el constructor.
     * Los dias laborables son los dias de la semana en los que el doctor tiene horarios
     *
     * @return una lista de Strings contieniendo los dias laborables del medico. Los strings pueden ser LUNES, MARTES, MIERCOLES... DOMINGO
     */
    private List<String> obtenerDiasHabiles() {

        List<String> dias = null;
        try {
            dias = doctorDao.diasHabiles(this.doctorSeleccionado.getId());
        } catch (SQLException ex) {
            Logger.getLogger(EditarFechaCitaDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dias;
    }

    /**
     * Convierte los dias recuperados como String a un &lt;EnumMap DayOfWeek , Boolean&gt;
     * Este map es usado mas tarde en  metodo #HabilitarDiasSeleccionados.
     *
     * @param diasObtenidos
     */
    private EnumMap<DayOfWeek, Boolean> Convertir(List<String> diasObtenidos) {
        Set<DayOfWeek> diasActivos = new HashSet<>();

        for (String dia : diasObtenidos) {
            DayOfWeek dayOfWeek = TraductorDias.aDayOfWeek(dia); // Obtenemos el equivalente de ese dia en DayOfWeek
            diasActivos.add(dayOfWeek);
        }
        EnumMap<DayOfWeek, Boolean> diasHabiles = new EnumMap<>(DayOfWeek.class);
        // Creamos un Enum con todos los dias de la semana y un bolean asociado.
        // usamos el atributo de la clase;
        diasHabiles = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek dia : DayOfWeek.values()) {
            diasHabiles.put(dia, diasActivos.contains(dia));
        }

        return diasHabiles;

    }

    /**
     * Este metodo solamente permite elegir dias a partir de hoy y en los que el doctor labore (que posea un horario);
     */
    private void HabilitarDiasSeleccionables(EnumMap<DayOfWeek, Boolean> diasHabiles) {
        this.view.getDatePicker().setDateSelectionAble((LocalDate date) -> {
            if (date.isAfter(LocalDate.now())) {
                return diasHabiles.get(date.getDayOfWeek());
            }
            return false;
        });
    }
    
    
    /**
     * Valida los campos de la vista.
     * @return True si todos los campos estan llenos, de lo contrario false
     */
    private boolean validarCampos() {
        if (this.pacienteSeleccionado == null
                || this.view.getCbEspecialidad().getSelectedItem() == null
                || this.doctorSeleccionado == null
                || this.view.getCbServicios().getSelectedItem() == null
                || !this.view.getDatePicker().isDateSelected()
                || this.view.getCbHorarios().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(view, "Todos los campos deben de estar lleno", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Recupera todos los campos de la vista AgendarUnaCita.
     * @return un objeto <code>Cita</code> con la informacion de todos lso campos
     */
    private Citas obtenerDatos() {
        Citas nuevaCita = new Citas();
        nuevaCita.setIdPaciente(this.pacienteSeleccionado.getId());
        nuevaCita.setIdEspecialidad(((Especialidad) this.view.getCbEspecialidad().getSelectedItem()).getId());
        nuevaCita.setIdDoctor(this.doctorSeleccionado.getId());
        nuevaCita.setIdServicio(((Servicio) this.view.getCbServicios().getSelectedItem()).getId());
        nuevaCita.setFecha(this.view.getDatePicker().getSelectedDate());
        nuevaCita.setIdHorarioDoctor(((Horario) this.view.getCbHorarios().getSelectedItem()).getId());
        return nuevaCita;
    }

    /**
     * Filtra los campos de la vista AgendarUnaCita
     */
    private void limpiarFiltros() {
        this.doctorSeleccionado = null;
        this.pacienteSeleccionado = null;
        this.view.limpiarCampos();
        this.view.desactivarDatePicker();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Filtrar un paciente
        if (e.getSource() == this.view.getBtnPacienteFiltrar()) {
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(this.view);

            ElegirPacienteDialog dialog = new ElegirPacienteDialog(ventanaPadre, true);
            PacienteDao pDAO = new PacienteDao();
            ElegirPacienteDialogController dialogController = new ElegirPacienteDialogController(dialog, pDAO);

            dialog.setLocationRelativeTo(ventanaPadre);
            dialog.setVisible(true);

            this.pacienteSeleccionado = dialogController.obtenerPaciente();
            this.view.mostrarPacienteElegido(pacienteSeleccionado);

        // Filtrar un doctor
        } else if (e.getSource() == this.view.getBtnDoctorFiltrar()) {
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(view);

            ElegirDoctorDialog dialogView = new ElegirDoctorDialog(ventanaPadre, true);
            DoctorLigeroDAO dDAO = new DoctorLigeroDAO();
            EspecialidadDao eDAO = new EspecialidadDao();
            ElegirDoctorDialogController dialogController = new ElegirDoctorDialogController(dialogView, dDAO, eDAO);

            dialogView.setLocationRelativeTo(ventanaPadre);
            dialogView.setVisible(true);

            this.doctorSeleccionado = dialogController.obtenerDoctorSeleccionado();
            view.mostrarDoctorElegido(doctorSeleccionado);
            this.HabilitarDiasSeleccionables(Convertir(obtenerDiasHabiles()));
            this.view.getCbHorarios().setSelectedIndex(0);
            this.view.activarDatePicker();
            
        // Agendar una cita
        } else if (e.getSource() == this.view.getBtnAgendarUnaCita()) {
            if (this.validarCampos()) {
                try {

                    double monto = ((Servicio) this.view.getCbServicios().getSelectedItem()).getPrecio();
                    Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(view);
                    MetodoDePagoDao metodoDao = new MetodoDePagoDao();

                    PagarUnaCitaDialog dialog = new PagarUnaCitaDialog(ventanaPadre, true, monto, metodoDao);
                    dialog.setLocationRelativeTo(ventanaPadre);
                    dialog.setVisible(true);
                    Integer idMetodoDePago = null;
                    idMetodoDePago = dialog.obtenerMetodoDePago();

                    if (idMetodoDePago != null) {
                        citasDao.AgendarUnaCita(this.obtenerDatos(), monto, idMetodoDePago);
                        JOptionPane.showMessageDialog(view, "La cita ha sido agendada correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                        this.limpiarFiltros();
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        // Limpiar los filtros
        } else if (e.getSource() == this.view.getBtnLimpiarFiltros()) {
            this.limpiarFiltros();

        }
    }

}
