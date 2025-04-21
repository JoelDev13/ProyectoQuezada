/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Frame;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.dao.citas.CitasDao;
import model.dao.especialidad.EspecialidadDao;
import model.dao.servicios.ServiciosDao;
import model.doctor.DoctorLigeroDTO;
import model.especialidad.Especialidad;
import model.servicios.Servicio;
import model.usuario.Usuario;
import view.Citas;
import view.EditarEstadoCitaDialog;

/**
 * Controlador que especializa el controlador del panel de citas para que pueda ser usado
 * en el panel de Agenda de doctor.
 * 
 * Este controlador cambia algunos comportamientos del controlador citas:
 * <ul>
 * <li>Deja fijo el doctor seleccionado con tal de que solamente se filtre para doctor que hizo el login</li>
 * <li>Los comboboxes de Especialidad y servicios ahora solo traen los servicios y especialidades relacionadas al doctor</li>
 * <li>Deshabilita la reagenda de citas, de tal manera que un doctor solamente pueda cambiar el estado de uan cita a COMPLETADA</li>
 * 
 * Esencialmente esos on los cambios que realiza esta especializacion. cualquier otro cambio es para mantener la funcionalidad original
 * del panel.
 * </ul>
 * @author luis-
 */
public class AgendaDoctorController extends CitasController {

    public AgendaDoctorController(Citas citasView, CitasDao citasDao, ServiciosDao serviciosDao, EspecialidadDao especialidadDao, Usuario usuario) {
        super(citasView, citasDao, serviciosDao, especialidadDao);
        DoctorLigeroDTO doctor = new DoctorLigeroDTO();
        doctor.setId(usuario.getId());
        doctor.setNombre(usuario.getNombre() + " " + usuario.getApellido());
        
        super.doctorSeleccionado = doctor;
        citasView.mostrarDoctorElegido(doctorSeleccionado);
        citasView.getBtnDoctorFiltrar().setEnabled(false);
        citasView.getBtnReAgendar().setEnabled(false);
        
        
        this.llenarCbEspecialidades();
        this.llenarCbServicios();
        
        this.listarCitasDelDoctor();
    }

    @Override
    protected void listarTodos() {
        /* 
            deje este metodo en blanco. En la clase padre este metodo es llamado en el constructor
            y debido a que, para ese punto, no se tiene ningun filtro, se obtendrian todos las citas
            del consultorio debido a la naturaleza del store procedure. Al dejar en blanco bordea el problema.
            No es la mejor solucion, pero es una.
        */
    }

    private void listarCitasDelDoctor() {
        model.citas.Citas filtros = citasView.obtenerDatosParaFiltrado(pacienteSeleccionado, doctorSeleccionado);

        System.out.println("DENTRO DEL BOTON DE FILTRAR");
        try {
            this.llenarTabla(citasDao.filtrarCitas(filtros));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(citasView, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    
    
    /**
     * Una vez seleccionada una cita, solamente se permite Reagendarla o cambiar su estado
     */
    @Override
    protected void entrarEstadoPreEdicion() {
        this.citasView.getBtnCitasFiltrar().setEnabled(false);
        this.citasView.getBtnLimpiarFiltros().setEnabled(false);
        this.citasView.getBtnEditarEstado().setEnabled(true);
        this.citasView.getBtnCancelar().setEnabled(true);
        this.citasView.getBtnReAgendar().setEnabled(false);
    }

    /**
     * Vuelve al estado normal del view. Solamente permite filtrar y borrar los filtros
     */
    @Override
    protected void entrarEstadoNormal() {
        this.citasView.getBtnCitasFiltrar().setEnabled(true);
        this.citasView.getBtnLimpiarFiltros().setEnabled(true);
        this.citasView.getBtnEditarEstado().setEnabled(true);
        this.citasView.getBtnCancelar().setEnabled(true);
        this.citasView.getBtnReAgendar().setEnabled(false);
    }

    @Override
    protected void limpiarFiltros() {
        pacienteSeleccionado = null;
        this.citasView.LimpiarPacienteSeleccionado();
        this.citasView.limpiarOtrosFiltros();
    }

    @Override
    protected void llenarCbEspecialidades() {
        if (doctorSeleccionado != null) {
            try {
                DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
                modelo.addElement(null);
                List<Especialidad> especialidades = especialidadDao.listarEspecialidadesDeUnMedico(this.doctorSeleccionado.getId());
                for (Especialidad esp : especialidades) {
                    modelo.addElement(esp);
                }
                citasView.getCbEspecialidad().setModel(modelo);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(citasView, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * LLena el ComboBox de servicios con Objetos <code>Servicio</code>
     */
    @Override
    protected void llenarCbServicios() {
        if (doctorSeleccionado != null) {
            try {
                DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<>();
                modelo.addElement(null); // para tener una seleeccion que sea "ningun servicio"
                List<Servicio> servicios = serviciosDao.listarServiciosDeUnMedico(this.doctorSeleccionado.getId());
                for (Servicio servicio : servicios) {
                    modelo.addElement(servicio);
                }
                citasView.getCbServicios().setModel(modelo);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(citasView, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void editarEstadoCitaSeleccionada() {
        model.citas.Citas citaOriginal = this.ObtenerCitaSelecciona();
        if (citaOriginal != null) {
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(citasView);
            EditarEstadoCitaDialog dialog = new EditarEstadoCitaDialog(ventanaPadre, true, citaOriginal, true);
            dialog.setLocationRelativeTo(ventanaPadre);
            dialog.setVisible(true);

            model.citas.Citas actualizacion = dialog.ObtenerDatosDeActualizacion();

            // Es posible que el usuario cierre la ventana en vez de seleccionar. por ello
            // validamos que el campo no sea nulo.
            if (actualizacion != null) {
                try {
                    citasDao.actualizarCita(actualizacion);
                    this.listarCitasDelDoctor();
                    JOptionPane.showMessageDialog(ventanaPadre, "Cita actualizada correctamente", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(citasView, e.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
                    e.printStackTrace();
                }
            }

        }
    }

}
