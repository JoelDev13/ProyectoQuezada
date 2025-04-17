/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.formdev.flatlaf.util.SwingUtils;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import model.dao.PacienteDao;
import model.dao.doctor.DoctorLigeroDAO;
import model.dao.especialidad.EspecialidadDao;
import model.doctor.DoctorLigeroDTO;
import model.paciente.PacienteDTO;
import view.Citas;
import view.ElegirDoctorDialog;
import view.ElegirPacienteDialog;

/**
 *
 * @author luis-
 */
public class CitasController implements ActionListener{
    /**
     * paciente usado para filtrar citas
     */
    private PacienteDTO pacienteSeleccionado; 
    
    /**
     * Doctor usado para filtrar citas
     */
    private DoctorLigeroDTO doctorSeleccionado;
    
    private Citas citasView;
    private DoctorLigeroDAO dDAO;

    public CitasController(Citas citasView) {
        this.citasView = citasView;
 
        System.out.println("ESTAS EN EL CONTROLADOR CITAS");

        this.citasView.getBtnPacienteFiltrar().addActionListener(this);
        this.citasView.getBtnDoctorFiltrar().addActionListener(this);
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

            PacienteDTO p = dialogController.obtenerPaciente();
            System.out.println(p.getId());
            System.out.println(p.getNombre());
            System.out.println(p.getApellido());
            System.out.println(p.getCedula());

        } else if (e.getSource() == citasView.getBtnDoctorFiltrar()){
            System.out.println("DESDE EL BOTON DOCTOR FILTRAR");
            Frame ventanaPadre = (Frame) SwingUtilities.getWindowAncestor(citasView);
            
            ElegirDoctorDialog dialogView = new ElegirDoctorDialog(ventanaPadre, true);
            DoctorLigeroDAO dDAO = new DoctorLigeroDAO();
            EspecialidadDao eDAO = new EspecialidadDao();
            ElegirDoctorDialogController dialogController = new ElegirDoctorDialogController(dialogView, dDAO, eDAO);
            
            dialogView.setLocationRelativeTo(ventanaPadre);
            dialogView.setVisible(true);
            
            doctorSeleccionado = dialogController.obtenerDoctorSeleccionado();

        }
    }

}
