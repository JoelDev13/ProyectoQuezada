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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.dao.PacienteDao;
import model.paciente.PacienteDTO;
import view.ElegirPacienteDialog;

/**
 * Esta clase, junto al view ElegirPacienteDialog, se encarga de filtrar,
 * seleccionar y recuperar un objeto Paciente para posterior uso de quien llame
 * a este dialogo.
 * @author luis-
 */
public class ElegirPacienteDialogController implements ActionListener {
    
    private ElegirPacienteDialog DialogView;
    private PacienteDao pDAO;
    private PacienteDTO pacienteSeleccionado = new PacienteDTO();
    

    public ElegirPacienteDialogController(ElegirPacienteDialog dg, PacienteDao pDAO) {
        this.DialogView = dg;
        this.pDAO = pDAO;
        
        this.DialogView.getBtnFiltrar().addActionListener(this);
        
  
        // Le agregamos un mouse adapter a la tabla de la vista, para que 
        // recupera la informacion del paciente elejido cuando el usuario hace
        // doble click en una fila.
        this.DialogView.getCustomTable().addMouseListener(new MouseAdapter () {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int fila = dg.getCustomTable().getSelectedRow();
                    if (fila != -1) {
                        pacienteSeleccionado.setId((int)dg.getCustomTable().getValueAt(fila, 0));
                        pacienteSeleccionado.setNombre((String)dg.getCustomTable().getValueAt(fila, 1));
                        pacienteSeleccionado.setApellido((String) dg.getCustomTable().getValueAt(fila, 2));
                        pacienteSeleccionado.setCedula((String) dg.getCustomTable().getValueAt(fila, 3));
                        dg.dispose();
                    }
                }
            }
        });

    }
    
    
    /**
     * Metodo que recupera la información del paciente elegido. Es ejecutado cuando
     * el dialogo ElegirPacienteDialog se cierra.
     * @return objeto <code>PacienteDTO</code> con la informacion del paciente elegido
     */
    public PacienteDTO obtenerPaciente() {
        return pacienteSeleccionado;
    }
    
    
    /**
     * Filtra los pacientes que cumplan las descripciones
     * @param e evento del boton de filtrado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            List<PacienteDTO> pacientes = pDAO.FiltrarPacientesDTO(DialogView.obtenerDatos());
            DefaultTableModel modelo = (DefaultTableModel) this.DialogView.getCustomTable().getModel();
            modelo.setRowCount(0);
            for (PacienteDTO p : pacientes) {
                Object[] fila = new Object[5];
                fila[0] = p.getId();
                fila[1] = p.getNombre();
                fila[2] = p.getApellido();
                fila[3] = p.getCedula();
                modelo.addRow(fila);
            }
            this.DialogView.getCustomTable().setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(ElegirPacienteDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

}
