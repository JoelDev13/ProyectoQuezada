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
 * Controlador de la vista panelServicios
 * @author luis-
 */
public class ServiciosController implements ActionListener{

    private panelServicios view;
    private ServiciosDao servicioDao;
    
    /**
     * Servicio seleccionado por el usuario
     */
    private Servicio servicioSeleccionado;

    public ServiciosController(panelServicios view, ServiciosDao servicioDao) {
        this.view = view;
        this.servicioDao = servicioDao;
        
        // asociamos los botones de la vista a este controlador
        this.view.getBtnCrear().addActionListener(this);
        this.view.getBtnActualizar().addActionListener(this);
        this.view.getBtnEliminar().addActionListener(this);
        this.view.getBtnCancelar().addActionListener(this);
        this.view.getBtnLimpiarFiltros().addActionListener(this);
        this.agregarFuncionalidad(this.view.getJList());
        
        this.view.EntrarEnEstadoNormal();
        
        this.listarTodos();
    }
    
    /**
     * Llena la lista con los datos recibidos.
     * @param servicios lista de servicios con que llenar la lista
     */
    private void llenarLista(List<Servicio> servicios) {
        DefaultListModel<Servicio> modelo = new DefaultListModel<>();
        for (Servicio s : servicios) {
            modelo.addElement(s);
        }
        this.view.getJList().setModel(modelo);
        
    }

    /**
     * Lista todos los servicios registrados en el hospital
     */
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
    
    /**
     * Añade un servicio nuevo al consultorio
     */
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

    /**
     * Actualiza la informacion del servicio seleccinado. Verifica que el servicio
     * no sea null para evitar nullPointer errors.
     */
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

    /**
     * Elimina el servicio seleccionado. Se verifica que el servicio seleccionado
     * no sea nulo para evitar errores NullPointer.
     */
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
        
        // boton crear
        if (e.getSource() == this.view.getBtnCrear()) {
            
            this.registrarServicio();
        } else if (e.getSource() == this.view.getBtnActualizar()) {
            this.ActualizarServicio();
        }
        
        
        // boton cancelar
        else if (e.getSource()== this.view.getBtnCancelar()) {
            this.view.EntrarEnEstadoNormal();
            this.view.limpiarCampos();
            this.servicioSeleccionado = null;
            
        }
        
        //boton eliminar
        else if (e.getSource() == this.view.getBtnEliminar()) {
            this.eliminarServicio();
        }
        
        // boton limpiar filtros
        else if (e.getSource() == this.view.getBtnLimpiarFiltros()) {
            this.view.limpiarCampos();
        }
        
    }
    
}
