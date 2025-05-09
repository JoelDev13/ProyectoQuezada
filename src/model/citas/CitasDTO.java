/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.citas;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Este DTO enriquecido esta pensado para ser usado en la Jtable del panel de Citas.
 *
 * @author luis-
 */
public class CitasDTO {

    private int id;
    private int idPaciente;
    private int idDoc;
    private int idServicio;
    private int idEspecialidad;
    private int idHorario;

    private String nombrePaciente;
    private String cedulaPaciente;
    private String nombreDoctor;
    private String especialidad;
    private String servicio;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;

    public CitasDTO() {
    }

    public CitasDTO(int id, int idPaciente, int idDoc, int idServicio, int idEspecialidad, int idHorario, String nombrePaciente, String cedulaPaciente, String nombreDoctor, String especialidad, String servicio, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idDoc = idDoc;
        this.idServicio = idServicio;
        this.idEspecialidad = idEspecialidad;
        this.idHorario = idHorario;
        this.nombrePaciente = nombrePaciente;
        this.cedulaPaciente = cedulaPaciente;
        this.nombreDoctor = nombreDoctor;
        this.especialidad = especialidad;
        this.servicio = servicio;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idPaciente
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the idDoc
     */
    public int getIdDoc() {
        return idDoc;
    }

    /**
     * @param idDoc the idDoc to set
     */
    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    /**
     * @return the idServicio
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @return the idEspecialidad
     */
    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad the idEspecialidad to set
     */
    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * @return the idHorario
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * @param idHorario the idHorario to set
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * @return the nombrePaciente
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * @param nombrePaciente the nombrePaciente to set
     */
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    /**
     * @return the cedulaPaciente
     */
    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    /**
     * @param cedulaPaciente the cedulaPaciente to set
     */
    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    /**
     * @return the nombreDoctor
     */
    public String getNombreDoctor() {
        return nombreDoctor;
    }

    /**
     * @param nombreDoctor the nombreDoctor to set
     */
    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
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
     * @return the servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the horaInicio
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFin
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
