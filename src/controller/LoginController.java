/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.dao.LoginDao;
import model.Usuario;
import view.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
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
        // Llama al método de LoginDao para verificar las credenciales con los nombres correctos
        Usuario usuario = loginDAO.verificarCredenciales(username, password);

        // Verifica si las credenciales son correctas
        if (usuario != null) {
            System.out.println("Inicio de sesion exitoso: " + usuario.getNombreUsuario());
            // Aquí se puede abrir otra ventana o mostrar un mensaje en la vista
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }

}
