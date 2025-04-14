package main;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import view.Dashboard;
import view.Login;
import model.dao.LoginDao;
import controller.LoginController;
import javax.swing.UIManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author la
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Component.focusWidth", 1);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Login loginView = new Login();
            LoginDao loginDAO = new LoginDao();
            LoginController loginController = new LoginController(loginView, loginDAO);
            loginView.setVisible(true);
        });
    }
    

}
