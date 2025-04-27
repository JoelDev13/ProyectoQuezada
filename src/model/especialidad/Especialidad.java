/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.especialidad;

/**
 * DTo que se encargara de transportar informacion de especialidades
 * @author luis-
 */
public class Especialidad {

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    private Integer id;
    private String descripcion;

    public Especialidad() { }

    public Especialidad(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

   
    @Override
    public String toString() {
        return getDescripcion();
    }
    
    
    
    
}
