/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.PacienteDao;
import model.paciente.Paciente;
import view.Pacientes;


/**
 * Controllador de la vista Pacientes.
 *
 * @author luis-
 * @see Paciente
 * @see PacienteDao
 * @see Pacientes
 */
public class PacienteController implements ActionListener{  
    
    private PacienteDao pacienteDAO;
    private Pacientes pacientesView;

    public PacienteController(PacienteDao pacienteDAO, Pacientes pacientesView) {
        this.pacienteDAO = pacienteDAO;
        this.pacientesView = pacientesView;
        
        // asociamos los botones botones de la vista con el controllador
        this.pacientesView.getBtnCrear().addActionListener(this);
        this.pacientesView.getBtnEditar().addActionListener(this);
        this.pacientesView.getBtnFiltrar().addActionListener(this);
        this.pacientesView.getBtnActualizar().addActionListener(this);
        this.pacientesView.getBtnCancelar().addActionListener(this);
        this.pacientesView.getBtnLimpiarFiltros().addActionListener(this);
        this.pacientesView.getBtnEliminar().addActionListener(this);
        
        // Cuando se hace click en alguna fila de la tabla de la vista, se entra en el modo 
        // pre-edicion de la vista.
        this.pacientesView.getTabla().addMouseListener(new MouseAdapter() { // creamos un listener para la tabla.
            @Override
            public void mousePressed(MouseEvent e) {
               pacientesView.entrarEstadoDePreEdicion();
            }
        });
        
        this.actualizarTabla();
    }
    
    /**
     * Lista todo los pacientes registrados en el consultorio medico
     */
    private void actualizarTabla() {
        try {
            List<Paciente> pacientes = pacienteDAO.listarPacientes();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DefaultTableModel modelo = (DefaultTableModel) pacientesView.getTabla().getModel();
            modelo.setRowCount(0);
            
            for (Paciente p : pacientes) {
                Object[] fila = new Object[9];
                //fila[0] = p.getId();
                fila[0] = p.getCedula();
                fila[1] = p.getNombre();
                fila[2] = p.getApellido();
                fila[3] = p.getSexo();
                fila[4] = p.getSeguro();
                fila[5] = p.getEmail();
                fila[6] = p.getTelefono();
                fila[7] = p.getFechaNacimiento().format(formato);
                fila[8] = p.getDireccion();
                modelo.addRow(fila);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    /**
     * Si se ha hecho click sobre una fila de la tabla de la vista, permite la edicion
     * de los datos de dicho paciente
     */
    private void editar() {
        int fila = this.pacientesView.getTabla().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this.pacientesView, "Debe seleccionar una fila para la edicion.", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            String cedula = (String) this.pacientesView.getTabla().getValueAt(fila, 0);
            String nombre = (String) this.pacientesView.getTabla().getValueAt(fila, 1);
            String apellido = (String) this.pacientesView.getTabla().getValueAt(fila, 2);
            String sexo = (String) this.pacientesView.getTabla().getValueAt(fila, 3);
            String seguro = (String) this.pacientesView.getTabla().getValueAt(fila, 4);
            String email = (String) this.pacientesView.getTabla().getValueAt(fila, 5);
            String telefono = (String) this.pacientesView.getTabla().getValueAt(fila, 6);
            String fecha_nacimiento = (String) this.pacientesView.getTabla().getValueAt(fila, 7);
            String direccion = (String) this.pacientesView.getTabla().getValueAt(fila, 8);
            
           this.pacientesView.getTxtCedula().setText(cedula);
           this.pacientesView.getTxtNombre().setText(nombre);
           this.pacientesView.getTxtApellido().setText(apellido);
           this.pacientesView.getCbSexo().setSelectedItem(sexo);
           this.pacientesView.getTxtSeguro().setText(seguro);
           this.pacientesView.getTxtEmail().setText(email);
           this.pacientesView.getTxtTelefono().setText(telefono);
           DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           LocalDate fecha = LocalDate.parse(fecha_nacimiento, formatoEntrada);
           this.pacientesView.getDatePicker().clearSelectedDate(); // Esto esta porque hay un bug en la libreria al poner una fecha igual ala anterior.
           this.pacientesView.getDatePicker().setSelectedDate(fecha);
           this.pacientesView.getDatePicker().repaint();
           this.pacientesView.getTxtDireccion().setText(direccion);         
           
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // filtrar un paciente
        if (e.getSource() == this.pacientesView.getBtnFiltrar()) {
            try {
                List<Paciente> pacientes = pacienteDAO.FiltrarPacientes(pacientesView.obtenerDatos());
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DefaultTableModel modelo = (DefaultTableModel) pacientesView.getTabla().getModel();
                modelo.setRowCount(0);

                for (Paciente p : pacientes) {
                    Object[] fila = new Object[9];
                    //fila[0] = p.getId();
                    fila[0] = p.getCedula();
                    fila[1] = p.getNombre();
                    fila[2] = p.getApellido();
                    fila[3] = p.getSexo();
                    fila[4] = p.getSeguro();
                    fila[5] = p.getEmail();
                    fila[6] = p.getTelefono();
                    fila[7] = p.getFechaNacimiento().format(formato);
                    fila[8] = p.getDireccion();
                    modelo.addRow(fila);
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(pacientesView, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                ex.fillInStackTrace();
            }
        }
        
        // Limpiar filtros
        else if (e.getSource() == this.pacientesView.getBtnLimpiarFiltros()) {
            this.pacientesView.limpiarCampos();
            this.actualizarTabla();
        }
        
        // agregar un paciente
        else if (e.getSource() == this.pacientesView.getBtnCrear()) {
            if (this.pacientesView.validarCampos()) {
                try {
                    pacienteDAO.agregarPaciente(pacientesView.obtenerDatos());
                    JOptionPane.showMessageDialog(pacientesView, "Paciente creado correctamente!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                    pacientesView.limpiarCampos();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(pacientesView, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                    ex.fillInStackTrace();
                }
            }
        }
        
        // Actualizar un paciente
        else if (e.getSource() == this.pacientesView.getBtnActualizar()) {
            if (this.pacientesView.validarCampos()) {
                try {
                    this.pacienteDAO.actualizarPaciente(pacientesView.obtenerDatos());
                    JOptionPane.showMessageDialog(pacientesView, "Paciente actualizado correctamente!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                    this.pacientesView.limpiarCampos();
                    this.pacientesView.entrarEstadoDeGuardado();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(pacientesView, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                    ex.fillInStackTrace();
                }
            }
        }
        
        // eliminar un paciente
        else if (e.getSource() == this.pacientesView.getBtnEliminar()) {

            int fila = this.pacientesView.getTabla().getSelectedRow();
            String cedula = (String) this.pacientesView.getTabla().getValueAt(fila, 0);
            if (fila == -1) {
                JOptionPane.showMessageDialog(this.pacientesView, "Debe seleccionar una fila para la edicion.", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int resp = JOptionPane.showConfirmDialog(
                    pacientesView,
                    "Estas seguro de que quieres eliminar este paciente",
                    "Seguro?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (resp == JOptionPane.YES_OPTION) {
                try {
                    pacienteDAO.eliminarPaciente(cedula);
                    JOptionPane.showMessageDialog(pacientesView, "Paciente eliminado correctamente!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(pacientesView, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    
 
        // Entrar en el modo de edicion (para actualizar un paciente)
        else if (e.getSource() == this.pacientesView.getBtnEditar()) {
            this.pacientesView.entrarEstadoDeEdicion();
            this.editar();
        }
        
        // salir del modo de edicion (para actualizar un paciente)
        else if (e.getSource() == this.pacientesView.getBtnCancelar()) {
            System.out.println("ME HAN TOCADO");
            this.pacientesView.entrarEstadoDeGuardado();
            this.pacientesView.limpiarCampos();
        }
        
    }
}
