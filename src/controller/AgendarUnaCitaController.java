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

/**
 *
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

        this.view.getCbEspecialidad().addActionListener((e) -> {
            Especialidad especialidadElegida = (Especialidad) this.view.getCbEspecialidad().getSelectedItem();
            this.llenarCbServicios(especialidadElegida.getId());
        });

        this.view.getDatePicker().addDateSelectionListener((dateSelectionEvent) -> {
            this.llenarCbHorarios(this.view.getDatePicker().getSelectedDate());
        });

        this.llenarCbEspecialidades();
    }

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
     * obtiene los dias laborales del medico entregado en el constructor. Los dias laborables son los dias de la semana en los que el doctor tiene horarios
     *
     * @return
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
     * Convierte los dias recuperados como String a un EnumMap de DayOfWeek , Boolean. Este map es usado mas tarde en #HabilitarDiasSeleccionados.
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

    private void llenarCbHorarios(LocalDate fecha) {
        try {
            String dia = TraductorDias.aString(fecha.getDayOfWeek());

            DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
            List<Horario> horarios = doctorDao.ObtenerHorariosDeUnDia(this.doctorSeleccionado.getId(), dia);
            for (Horario horario : horarios) {
                modelo.addElement(horario);
            }
            this.view.getCbHorarios().setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.getBtnPacienteFiltrar()) {
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(this.view);

            ElegirPacienteDialog dialog = new ElegirPacienteDialog(ventanaPadre, true);
            PacienteDao pDAO = new PacienteDao();
            ElegirPacienteDialogController dialogController = new ElegirPacienteDialogController(dialog, pDAO);

            dialog.setLocationRelativeTo(ventanaPadre);
            dialog.setVisible(true);

            this.pacienteSeleccionado = dialogController.obtenerPaciente();
            this.view.mostrarPacienteElegido(pacienteSeleccionado);

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

        } else if (e.getSource() == this.view.getBtnAgendarUnaCita()) {
            System.out.println("Todavia no esta implementado");
        }
    }

}
