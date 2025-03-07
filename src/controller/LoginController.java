/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Usuario;
import model.dao.LoginDao;
import view.Login;

/**
 *
 * @author la
 */
public class LoginController implements ActionListener{
        
  
   
   
    
    public Usuario login(String nombreUsuario, String contrasena) {
        LoginDao loginDao = new LoginDao(); 
        Usuario usuario = loginDao.verificarCredenciales(nombreUsuario, contrasena);
        
        //Si el usuario es válido se puede procede a la proxima vista 
        if (usuario != null) {
            System.out.println("Usuario autenticado: " + usuario.getNombreUsuario());
        } else {
            System.out.println("Credenciales incorrectas");
        }
        
        return usuario;
    }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
