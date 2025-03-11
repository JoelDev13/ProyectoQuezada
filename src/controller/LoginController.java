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

/**
 *
 * @author la
 */
public class LoginController implements ActionListener{
        
    private LoginDao model;
    private Login view;

    public LoginController(LoginDao model, Login view) {
    this.model = model;
    this.view = view;
    
    // Agregar el evento de acción al botón correcto
    this.view.getGreenRoundArrowButtoms1().addActionListener(this);
}

@Override
    public void actionPerformed(ActionEvent e) {
    String username = view.getjTextField2().getText(); 
    String password = view.getjTextField1().getText(); 

    // Verifica si los campos están vacíos
    if (username.isEmpty() || password.isEmpty()) {
        System.out.println("Los campos no pueden estar vacios");
        return;
    }

    // Llama al método de LoginDao para verificar las credenciales con los nombres correctos
    Usuario usuario = model.verificarCredenciales(username, password);
    
    // Verifica si las credenciales son correctas
    if (usuario != null) {
        System.out.println("Inicio de sesion exitoso: " + usuario.getNombreUsuario());
        // Aquí se puede abrir otra ventana o mostrar un mensaje en la vista
    } else {
        System.out.println("Usuario o contraseña incorrectos");
    }
}

 }


