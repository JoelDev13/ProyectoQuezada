/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author la
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String email;
    private byte[] imagen;
    private String rol;

    public Usuario() {}

    public Usuario(int id, String nombreUsuario, String email, byte[] imagen, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.imagen = imagen;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        StringBuffer formatString = new StringBuffer("");
        return  formatString.append("\n" + getNombreUsuario() + "\n" + getRol()).toString();
    }
    
    
    
    
    
}