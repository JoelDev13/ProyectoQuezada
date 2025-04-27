package controller;

import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.doctor.Doctor;
import model.dao.doctor.DoctorDAO;
import view.Doctores;

public class DoctoresController {
    private final Doctores vista;
    private final DoctorDAO dao;

    public DoctoresController(Doctores vista, DoctorDAO dao) {
        this.vista = vista;
        this.dao = dao;
        configurarEventos();
        cargarDoctoresIniciales(); 
    }

    private void configurarEventos() {
        
        vista.getBtnFiltrar1().addActionListener(e -> filtrarDoctores());
        
    
        vista.getTxtFiltro().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filtrarDoctores(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filtrarDoctores(); }
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
     
        vista.getComboEspecialidad().addActionListener(e -> filtrarDoctores());
    }

    private void cargarDoctoresIniciales() {
        List<Doctor> doctores = dao.filtrarDoctores(null, null);
        vista.actualizarTabla(doctores);
    }

    private void filtrarDoctores() {
    String filtro = vista.getTextoFiltro();
        Integer id = vista.getEspecialidadSeleccionada().getId();
        List<Doctor> doctores = dao.filtrarDoctores(filtro, id);
        vista.actualizarTabla(doctores);
        }
}