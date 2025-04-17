/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.doctor;

/**
 * Este DTO esta pensado para ser usado en los Dialog que filtren Doctores
 *
 * @author luis-
 */
public class DoctorLigeroDTO {

    private long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private byte[] imagen;

    public DoctorLigeroDTO() {    }

    public DoctorLigeroDTO(long id, String nombre, String apellido, String especialidad, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.imagen = imagen;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the perfil
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the perfil to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

 

    
    
    

}
