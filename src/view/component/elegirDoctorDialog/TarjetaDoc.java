/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component.elegirDoctorDialog;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.doctor.DoctorLigeroDTO;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author luis-
 */
public class TarjetaDoc extends JPanel {

    private DoctorLigeroDTO doctor;

    public TarjetaDoc(DoctorLigeroDTO doctor) {
        this.doctor = doctor;
        init();

    }

    private void init() {
        setLayout(new MigLayout("", "", "fill"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "arc: 15;"
                + "background:#e9e9e9;");

        JPanel perfil = crearPerfil();
        JPanel descripcion = crearDescripcion();

        this.add(perfil);
        this.add(descripcion);
    }

    private JPanel crearPerfil() {

        JPanel perfil = new JPanel(new MigLayout("fill, insets 0", "[fill]", "[top]"));
        JLabel labelPerfil = new JLabel();
        labelPerfil.setBounds(0, 0, 100, 100);

        if (doctor.getImagen() == null) {
//            labelPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/component/menu/icons/avatar/p2.png")));
            try {
                URL url = getClass().getResource("/view/component/menu/icons/avatar/p2.png");
                BufferedImage imagenOriginal = ImageIO.read(url);
                Image reescalado = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(reescalado);
                labelPerfil.setIcon(icon);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else {
            try (InputStream in = new ByteArrayInputStream(doctor.getImagen())) {
                BufferedImage imagenOriginal = ImageIO.read(in);
                Image reescalado = imagenOriginal.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(reescalado);
                labelPerfil.setIcon(icon);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        perfil.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        labelPerfil.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        perfil.add(labelPerfil);
        return perfil;
    }

    private JPanel crearDescripcion() {
        JPanel descripcion = new JPanel(new MigLayout("wrap", "[]", "[][]"));
        descripcion.putClientProperty(FlatClientProperties.STYLE, "background:null;");

        JLabel labelNombre = new JLabel(doctor.getNombre() + " " + doctor.getApellido());
        labelNombre.putClientProperty(FlatClientProperties.STYLE, "font:bold +1;");

        JLabel labelEspecialidad = new JLabel(doctor.getEspecialidad());
        labelEspecialidad.putClientProperty(FlatClientProperties.STYLE,"background:null;");

        descripcion.add(labelNombre, "");
        descripcion.add(labelEspecialidad);
        return descripcion;
    }
    
    public DoctorLigeroDTO getDoctor() {
        return doctor;
    }
}
