package main;

import view.Login;
import model.dao.LoginDao;
import controller.LoginController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Joel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
    
        Login view = new Login(); // Vista
        LoginDao model = new LoginDao(); // Modelo
        LoginController controller = new LoginController(model, view); // Controlador
  }
