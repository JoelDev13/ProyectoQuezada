/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.dao.doctor.DoctorLigeroDAO;
import model.dao.especialidad.EspecialidadDao;
import model.doctor.DoctorLigeroDTO;
import model.especialidad.Especialidad;
import view.ElegirDoctorDialog;
import view.component.elegirDoctorDialog.TarjetaDoc;

/**
 * Esta clase, junto ElegirDoctorDialog, se encarga de filtrar, seleccionar y recuperar un objeto DoctorDto.
 *
 * @author luis-
 */
public class ElegirDoctorDialogController implements ActionListener {

    private ElegirDoctorDialog dialog;
    private DoctorLigeroDAO dDAO;
    private EspecialidadDao espDAO;
    private DoctorLigeroDTO doctorSeleccionado;

    public ElegirDoctorDialogController(ElegirDoctorDialog dialog, DoctorLigeroDAO dDAO, EspecialidadDao espDAO) {
        this.dialog = dialog;
        this.dDAO = dDAO;
        this.espDAO = espDAO;
        this.dialog.getBtnFiltrar().addActionListener(this);

        this.llenarCbEspecialidad();
        this.filtrarDoctores();
    }

    /**
     * Llena el ComboBox con las especialidades que estan registradas
     */
    private void llenarCbEspecialidad() {
        try {
            List<Especialidad> especialidades = espDAO.listarEspecialidades();
            DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
            modelo.addElement(" ");

            for (Especialidad e : especialidades) {
                modelo.addElement(e.getDescripcion());
            }

            dialog.getCbEspecialidad().setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Filtra los doctores usando las descripciones dadas por el usuario. Si el usuario no da ninguna descripcion, se traen todos los registros
     */
    private void filtrarDoctores() {
        JPanel panel = dialog.getPanelMig();
        panel.removeAll();

        try {
            List<DoctorLigeroDTO> doctores = dDAO.filtrarDoctores(dialog.obtenerDatos());
            for (DoctorLigeroDTO doctor : doctores) {
                TarjetaDoc tarjeta = new TarjetaDoc(doctor);
                this.agregarFuncionalidad(tarjeta);
                panel.add(tarjeta, "growx");
            }

            panel.revalidate();
            panel.repaint();
            JScrollPane scroll = (JScrollPane) panel.getParent().getParent(); // aparentemente, un Scroll pane esta compuesto de dos panels.
            scroll.revalidate();
            scroll.repaint();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * este metodo le agrega comportamiento a las tarjetas que se muestran en el dialogo ElegirDoctorDialog. Estas cambian de color cuando el mouse entra y sale de ellas y recuperan la informacion del doctor en la que haga click
     *
     * @param tarjeta objeto <code>TarjetaDoc</code> al que se le agregara la funcionalidad
     */
    private void agregarFuncionalidad(TarjetaDoc tarjeta) {
        tarjeta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tarjeta.setBackground(Color.decode("#dddddd"));
                tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tarjeta.setBackground(Color.decode("#e9e9e9"));
                tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                doctorSeleccionado = tarjeta.getDoctor();
                dialog.dispose();

            }

        });
    }

    /**
     * Este metodo recupera la informacion del doctor seleccionado por el usuario.
     * @return objeto <code>DoctorLigeroDTO</code> con la informacion del medico elegido
     */
    public DoctorLigeroDTO obtenerDoctorSeleccionado() {
        return doctorSeleccionado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dialog.getBtnFiltrar()) {
            this.filtrarDoctores();
        }
    }

}
