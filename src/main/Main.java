package main;

import com.formdev.flatlaf.FlatLightLaf;
import view.Login;
import model.dao.LoginDao;
import controller.LoginController;
import javax.swing.UIManager;


/**
 * Main
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
