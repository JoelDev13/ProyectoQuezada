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
import javax.swing.JOptionPane;
import model.dao.especialidad.EspecialidadDao;
import model.especialidad.Especialidad;
import view.PanelEspecialidades;

/**
 *
 * @author YELIANA
 */
public class EspecialidadesController implements ActionListener {

    private EspecialidadDao especialidadDao;
    private PanelEspecialidades especialidadesView;

    public EspecialidadesController(EspecialidadDao especialidadDao, PanelEspecialidades especialidadesView) {
        this.especialidadDao = especialidadDao;
        this.especialidadesView = especialidadesView;

        this.especialidadesView.getBtnAgregarEspecialidad().addActionListener(this);
        this.especialidadesView.getBtnEliminarEspecialidad().addActionListener(this);
       
        this.listarTodos();
        
    }
    
    private void llenarJlist(List<Especialidad> especialides) {
        DefaultListModel<Especialidad> modelo = new DefaultListModel<>();
        for (Especialidad e : especialides) {
            modelo.addElement(e);
        }
        this.especialidadesView.getJList().setModel(modelo);
    }
    
    private void listarTodos() {
        try {
            this.llenarJlist(especialidadDao.listarEspecialidades());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(especialidadesView, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.especialidadesView.getBtnAgregarEspecialidad()) {
           
            try {
                especialidadDao.agregarEspecialidad(this.especialidadesView.ObtenerDatos());
                this.llenarJlist(especialidadDao.listarEspecialidades());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(especialidadesView, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == this.especialidadesView.getBtnEliminarEspecialidad()) {
        this.eliminarEspecialidad(); // ¡Esta línea faltaba!
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
        boolean eliminado = especialidadDao.eliminarEspecialidad(especialidadSeleccionada.getId());
        if (eliminado) {
            JOptionPane.showMessageDialog(especialidadesView, "Especialidad eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.listarTodos(); // Refresca la lista
        } else {
            JOptionPane.showMessageDialog(especialidadesView, "No se pudo eliminar la especialidad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        

    }
    
    
    
    
}
}