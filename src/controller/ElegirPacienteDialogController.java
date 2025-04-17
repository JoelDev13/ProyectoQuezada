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
 *
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
    
    
    public PacienteDTO obtenerPaciente() {
        return pacienteSeleccionado;
    }
    
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
