/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.horario;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Dto que transportara informacion de horarios.
 * @author luis-
 */
public class Horario {
    private int id;
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int idDoc;

    public Horario() {
    }

    public Horario(int id, String dia, LocalTime horaInicio, LocalTime horaFin, int idDoc) {
        this.id = id;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idDoc = idDoc;
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
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
        this.dia = dia;
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
    
    @Override
    public String toString() {
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        return horaInicio.format(formatoHora) + " - " + horaFin.format(formatoHora);
    }

}
