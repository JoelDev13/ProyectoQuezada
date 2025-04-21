package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author la
 */
public class MetodoDePagoModel {
    
    private int id;
    private String descripcion;

    // Constructor vacío
    public MetodoDePagoModel() {}

    // Constructor con id y descripcion
    public MetodoDePagoModel(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Constructor solo con descripcion
    public MetodoDePagoModel(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos getter y setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
   public String toString() {
    return descripcion;
}

}


