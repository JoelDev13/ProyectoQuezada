/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.citas;

import java.time.LocalDate;

/**
 * Este DTO esta planeado para ser usado en los filtros e insercciones.
 * Con tal de que podamos usar Nulls (para filtrar o no en la consulta)
 * usaremos la clase Integer en vez del dato primitivo int
 * @author luis-
 */


public class Citas {
    private Integer id;
    private Integer idPaciente;
    private Integer idDoctor;
    private Integer idHorarioDoctor;
    private Integer idServicio;
    private Integer idEspecialidad;
    private LocalDate fecha;
    private String estado;
    
    // Estos son unos agregados para poder manejar estas operaciones
    // mas facilmente desde la APP
    private LocalDate fechaInicioFiltro;
    private LocalDate fechaFinFiltro;
    
    public Citas() {}

    public Citas(Integer id, Integer idPaciente, Integer idDoctor, Integer idHorarioDoctor, Integer idServicio, Integer idEspecialidad, LocalDate fecha, String estado, LocalDate fechaInicioFiltro, LocalDate fechaFinFiltro) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idDoctor = idDoctor;
        this.idHorarioDoctor = idHorarioDoctor;
        this.idServicio = idServicio;
        this.idEspecialidad = idEspecialidad;
        this.fecha = fecha;
        this.estado = estado;
        this.fechaInicioFiltro = fechaInicioFiltro;
        this.fechaFinFiltro = fechaFinFiltro;
    }

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
     * @return the idPaciente
     */
    public Integer getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the idDoctor
     */
    public Integer getIdDoctor() {
        return idDoctor;
    }

    /**
     * @param idDoctor the idDoctor to set
     */
    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    /**
     * @return the idHorarioDoctor
     */
    public Integer getIdHorarioDoctor() {
        return idHorarioDoctor;
    }

    /**
     * @param idHorarioDoctor the idHorarioDoctor to set
     */
    public void setIdHorarioDoctor(Integer idHorarioDoctor) {
        this.idHorarioDoctor = idHorarioDoctor;
    }

    /**
     * @return the idServicio
     */
    public Integer getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @return the idEspecialidad
     */
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad the idEspecialidad to set
     */
    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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

    /**
     * @return the fechaInicioFiltro
     */
    public LocalDate getFechaInicioFiltro() {
        return fechaInicioFiltro;
    }

    /**
     * @param fechaInicioFiltro the fechaInicioFiltro to set
     */
    public void setFechaInicioFiltro(LocalDate fechaInicioFiltro) {
        this.fechaInicioFiltro = fechaInicioFiltro;
    }

    /**
     * @return the fechaFinFiltro
     */
    public LocalDate getFechaFinFiltro() {
        return fechaFinFiltro;
    }

    /**
     * @param fechaFinFiltro the fechaFinFiltro to set
     */
    public void setFechaFinFiltro(LocalDate fechaFinFiltro) {
        this.fechaFinFiltro = fechaFinFiltro;
    }



   
    
}
