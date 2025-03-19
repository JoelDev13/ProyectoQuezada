package main;

import view.Dashboard;
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
            Login loginView = new Login();
            LoginDao loginDAO = new LoginDao();
            LoginController loginController = new LoginController(loginView, loginDAO);
            loginView.setVisible(true);
        });
    }

}
