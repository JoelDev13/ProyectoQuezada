/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import view.ElegirDoctorDialog;
import view.ElegirPacienteDialog;

/**
 *
 * @author luis-
 */
public class CitasController implements ActionListener {

    // Atributos usados para guardar la seleccion del usuario
    private PacienteDTO pacienteSeleccionado;
    private DoctorLigeroDTO doctorSeleccionado;

    private Citas citasView;
    private CitasDao citasDao;
    private ServiciosDao serviciosDao;
    private EspecialidadDao especialidadDao;

    public CitasController(Citas citasView, CitasDao citasDao, ServiciosDao servicioDao, EspecialidadDao especialidadDao) {
        this.citasView = citasView;
        this.citasDao = citasDao;
        this.serviciosDao = servicioDao;
        this.especialidadDao = especialidadDao;
        System.out.println("ESTAS EN EL CONTROLADOR CITAS");
        this.llenarCbEspecialidades();
        this.llenarCbServicios();

        this.citasView.getBtnPacienteFiltrar().addActionListener(this);
        this.citasView.getBtnDoctorFiltrar().addActionListener(this);
        this.citasView.getBtnCitasFiltrar().addActionListener(this);
        this.citasView.getBtnLimpiarFiltros().addActionListener(this);
        this.citasView.getBtnEditar().addActionListener(this);
    }
    


    private void llenarTabla(List<CitasDTO> citas) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DefaultTableModel modelo = (DefaultTableModel) citasView.getCustomTable().getModel();
            modelo.setRowCount(0);
        for (CitasDTO cita : citas) {
            Object[] fila = new Object[9];
            //fila[0] = p.getId();
            fila[0] = cita.getId();
            fila[1] = cita.getFecha().format(formato);
            fila[2] = cita.getEstado();
            fila[3] = cita.getCedulaPaciente();
            fila[4] = cita.getNombrePaciente();
            fila[5] = cita.getNombreDoctor();
            fila[6] = cita.getServicio();
            fila[7] = cita.getEspecialidad();
            // cita.getHoraInicio().format(formato) + " - " + cita.getHoraFin().format(formato)
            fila[8] = ("Prueba");
            modelo.addRow(fila);
        }
    }
    
    private void llenarCbEspecialidades() {
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

    private void llenarCbServicios() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ENTRASTE AL ACTION");

        if (e.getSource() == citasView.getBtnPacienteFiltrar()) {
            System.out.println("DESDE BOTON PACIENTE FILTRAR ");
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(citasView);

            ElegirPacienteDialog dialog = new ElegirPacienteDialog(ventanaPadre, true);
            PacienteDao pDAO = new PacienteDao();
            ElegirPacienteDialogController dialogController = new ElegirPacienteDialogController(dialog, pDAO);

            dialog.setLocationRelativeTo(ventanaPadre);
            dialog.setVisible(true);

            pacienteSeleccionado = dialogController.obtenerPaciente();
            citasView.mostrarPacienteElegido(pacienteSeleccionado);

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

            
            
        } else if (e.getSource() == citasView.getBtnCitasFiltrar()) {
            model.citas.Citas filtros = citasView.obtenerDatosParaFiltrado(pacienteSeleccionado, doctorSeleccionado);
            
            System.out.println("DENTRO DEL BOTON DE FILTRAR");
            try {
                this.llenarTabla(citasDao.filtrarCitas(filtros));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(citasView, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
    }
    
    

}
