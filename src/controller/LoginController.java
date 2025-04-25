/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.dao.LoginDao;
import model.usuario.Usuario;
import view.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.Dashboard;

/**
 *  Esta clase maneja la autentificacion de los usuarios
 * @author la
 */
public class LoginController implements ActionListener {

    private LoginDao loginDAO;
    private Login loginView;

    public LoginController(Login loginView, LoginDao loginDAO) {
        this.loginView = loginView;
        this.loginDAO = loginDAO;

        // Agregar el evento de acción al botón correcto
        this.loginView.getBtnLogin().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = loginView.getTxtUser().getText().trim();
        String password = loginView.getTxtPasword().getText().trim();

        // Verifica si los campos están vacíos
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    loginView,
                    "Ninguno de los 2 campos de textos pueden estar vacios!",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        System.out.println(username + " " + password);
        Usuario usuario;
        try {
            usuario = loginDAO.verificarCredenciales(username, password);
            
            JOptionPane.showMessageDialog(
                    loginView,
                    "Conexion Exitosa! User: " + usuario.getUsuario() + " Rol:" + usuario.getRol(),
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE
            );
            
            Dashboard dashboard = new Dashboard(usuario);
            dashboard.setVisible(true);
            loginView.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    loginView,
                    ex.getMessage(), // Ahora la base de datos envia la descripcion del error !
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

    }
}
