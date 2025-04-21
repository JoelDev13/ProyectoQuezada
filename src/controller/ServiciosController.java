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
import model.dao.servicios.ServiciosDao;
import model.servicios.Servicio;
import view.panelServicios;

/**
 * Clase controlladora de la vista panelServicios
 * @author luis-
 */
public class ServiciosController implements ActionListener{

    private panelServicios view;
    private ServiciosDao servicioDao;
    
    private Servicio servicioSeleccionado;

    public ServiciosController(panelServicios view, ServiciosDao servicioDao) {
        this.view = view;
        this.servicioDao = servicioDao;
        
        this.view.getBtnCrear().addActionListener(this);
        this.view.getBtnActualizar().addActionListener(this);
        this.view.getBtnEliminar().addActionListener(this);
        this.view.getBtnCancelar().addActionListener(this);
        this.view.getBtnLimpiarFiltros().addActionListener(this);
        this.agregarFuncionalidad(this.view.getJList());
        
        this.view.EntrarEnEstadoNormal();
        
        this.listarTodos();
    }
    
    private void llenarLista(List<Servicio> servicios) {
        DefaultListModel<Servicio> modelo = new DefaultListModel<>();
        for (Servicio s : servicios) {
            modelo.addElement(s);
        }
        this.view.getJList().setModel(modelo);
        
    }

    private void listarTodos() {
        try {
            this.llenarLista(servicioDao.listarServicios());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void agregarFuncionalidad(JList list) {
        list.addListSelectionListener((e) -> {
           
            this.servicioSeleccionado = (Servicio) list.getSelectedValue();
            this.view.mostrarServicio(servicioSeleccionado);
            this.view.EntrarEnEstadoEdicion();
            
        });
        
    }
    
    private void registrarServicio() {
        try {
            if (this.view.validarCampos()) {
                this.servicioDao.registrarServicio(this.view.obtenerDatos());
                JOptionPane.showMessageDialog(view, "El servicio se creo correctamente", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                this.listarTodos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void ActualizarServicio() {
        try {
            if (servicioSeleccionado != null) {
                Servicio nuevosDatos = new Servicio();
                nuevosDatos = this.view.obtenerDatos();
                
                Servicio actualizacion = new Servicio();
                actualizacion.setId(this.servicioSeleccionado.getId());
                actualizacion.setDescripcion(nuevosDatos.getDescripcion());
                actualizacion.setPrecio(nuevosDatos.getPrecio());
                
                this.servicioDao.actualizarServicio(actualizacion);
                JOptionPane.showMessageDialog(view, "El servicio se actualizo correctamente", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                this.listarTodos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }

    }


    private void eliminarServicio() {
        try {
            if (servicioSeleccionado != null) {
                this.servicioDao.eliminarServicio(this.servicioSeleccionado.getId());
                JOptionPane.showMessageDialog(view, "El servicio se elimino correctamente", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                this.listarTodos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.view.getBtnCrear()) {
            
            this.registrarServicio();
        } else if (e.getSource() == this.view.getBtnActualizar()) {
            this.ActualizarServicio();
        }
        
        
        else if (e.getSource()== this.view.getBtnCancelar()) {
            this.view.EntrarEnEstadoNormal();
            this.view.limpiarCampos();
            this.servicioSeleccionado = null;
            
        }
        
        else if (e.getSource() == this.view.getBtnEliminar()) {
            this.eliminarServicio();
        }
        
        else if (e.getSource() == this.view.getBtnLimpiarFiltros()) {
            this.view.limpiarCampos();
        }
        
    }
    
}
