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
    private String nombre;
    private String apellido;
    private String telefono;
    private String usuario;
    private String contrasena;
    private String email;
    private byte[] imagen;
    private String rol;

    public Usuario() {}

    public Usuario(int id, String usuario, String contrasena, String email, byte[] imagen, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.nombre = apellido;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.email = email;
        this.imagen = imagen;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }
 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
        return  formatString.append("\n" + getUsuario() + "\n" + getRol()).toString();
    }

   
    }