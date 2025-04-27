/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.especialidad.EspecialidadDao;
import model.dao.servicios.ServiciosDao;
import model.especialidad.Especialidad;
import model.servicios.Servicio;
import view.PanelEspecialidades;

/**
 *
 * @author YELIANA
 */
public class EspecialidadesController implements ActionListener {

    private EspecialidadDao especialidadDao;
    private ServiciosDao servicioDao;
    private PanelEspecialidades especialidadesView;

    public EspecialidadesController(EspecialidadDao especialidadDao, PanelEspecialidades especialidadesView, ServiciosDao servicioDao) {
        this.especialidadDao = especialidadDao;
        this.especialidadesView = especialidadesView;
        this.servicioDao = servicioDao;

        this.especialidadesView.getBtnAgregarEspecialidad().addActionListener(this);
        this.especialidadesView.getBtnEliminarEspecialidad().addActionListener(this);
        this.especialidadesView.getBtnActualizarServiciosAsociados().addActionListener(this);

        // Hacemos que se cuando se haga click en una especialidad, la tabla de
        // servicios asociados se rellene automaticamente con los servicios
        // asociados a dicha especialidad.
        this.agregarFuncionalidad(this.especialidadesView.getJList());

        this.listarTodasLasEspecialidades();

    }

    private void llenarJlist(List<Especialidad> especialides) {
        DefaultListModel<Especialidad> modelo = new DefaultListModel<>();
        for (Especialidad e : especialides) {
            modelo.addElement(e);
        }
        this.especialidadesView.getJList().setModel(modelo);
    }

    private void listarTodasLasEspecialidades() {
        try {
            this.llenarJlist(especialidadDao.listarEspecialidades());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(especialidadesView, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarFuncionalidad(JList list) {
        list.addListSelectionListener((e) -> {
            System.out.println("HOLA");
            this.rellenarServiciosAsociados((Especialidad) list.getSelectedValue());
        });

    }

    private void rellenarServiciosAsociados(Especialidad esp) {
        if (esp != null) {
            try {
                List<Servicio> todosLosServicios = servicioDao.listarServicios();
                List<Servicio> serviciosAsociados = servicioDao.listarServiciosDeUnaEspecialidad(esp.getId());
                DefaultTableModel modelo = (DefaultTableModel) this.especialidadesView.getTableServiciosAsociados().getModel();
                modelo.setRowCount(0);

                for (Servicio s : todosLosServicios) {
                    Object[] fila = new Object[3];
                    fila[0] = s.getId();
                    fila[1] = s.getDescripcion();
                    fila[2] = false;
                    modelo.addRow(fila);
                }

                /*
                los servicios vienen ordenados por el ID, por ende
                se puede usar s.getID() - 1 para ir a la fila correcta
                de manera directa.
                 */
                for (Servicio s : serviciosAsociados) {
                    modelo.setValueAt(true, s.getId() - 1, 2);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(especialidadesView, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private void crearEspecialidad() {

        try {
            especialidadDao.agregarEspecialidad(this.especialidadesView.ObtenerDatos());
            JOptionPane.showMessageDialog(especialidadesView, "La especialidad se ha creado correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            this.listarTodasLasEspecialidades();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(especialidadesView, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void eliminarEspecialidad() {
        Especialidad especialidadSeleccionada = (Especialidad) especialidadesView.getJList().getSelectedValue();
        if (especialidadSeleccionada == null) {
            JOptionPane.showMessageDialog(especialidadesView, "Selecciona una especialidad primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                especialidadesView,
                "¿Eliminar la especialidad '" + especialidadSeleccionada.getDescripcion() + "'?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                especialidadDao.eliminarEspecialidad(especialidadSeleccionada.getId());
                this.listarTodasLasEspecialidades();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(especialidadesView, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private void actualizarServiciosAsociados() {
        Especialidad especialidadSeleccionada = (Especialidad) especialidadesView.getJList().getSelectedValue();
        if (especialidadSeleccionada == null) {
            JOptionPane.showMessageDialog(especialidadesView, "Selecciona una especialidad primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            this.especialidadDao.eliminarServiciosAsociadosDeEstaEspecialidad(especialidadSeleccionada.getId());
            int cantidadFilas = this.especialidadesView.getTableServiciosAsociados().getRowCount();

            for (int fila = 0; fila < cantidadFilas; fila++) {
                boolean checked = (boolean) this.especialidadesView.getTableServiciosAsociados().getValueAt(fila, 2);
                if (checked) {
                    int idServicio = (int) this.especialidadesView.getTableServiciosAsociados().getValueAt(fila, 0);
                    System.out.println("Especilidad" + " " + especialidadSeleccionada.getId());
                    System.out.println("Servicio" + " " + idServicio);
                    this.especialidadDao.asociarServiciosAespecialidad(especialidadSeleccionada.getId(), idServicio);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(especialidadesView, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }

        JOptionPane.showMessageDialog(especialidadesView, "Se han actualizado correctamente los servicios asociados", "Informacion", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.especialidadesView.getBtnAgregarEspecialidad()) {
            this.crearEspecialidad();

        } else if (e.getSource() == this.especialidadesView.getBtnEliminarEspecialidad()) {
            this.eliminarEspecialidad(); // ¡Esta línea faltaba!
        } else if (e.getSource() == this.especialidadesView.getBtnActualizarServiciosAsociados()) {
            this.actualizarServiciosAsociados();
        }

    }

}
