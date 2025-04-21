/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * clase usada para insertar en la tabla de historico de pago
 * @author luis-
 */
public class HistorialPagoModel {

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
     * @return the idCita
     */
    public int getIdCita() {
        return idCita;
    }

    /**
     * @param idCita the idCita to set
     */
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * @return the idMetodoDePago
     */
    public int getIdMetodoDePago() {
        return idMetodoDePago;
    }

    /**
     * @param idMetodoDePago the idMetodoDePago to set
     */
    public void setIdMetodoDePago(int idMetodoDePago) {
        this.idMetodoDePago = idMetodoDePago;
    }
    private int id;
    private int idCita;
    private double monto;
    private int idMetodoDePago;

    public HistorialPagoModel() {
    }

    public HistorialPagoModel(int id, int idCita, double monto, int idMetodoDePago) {
        this.id = id;
        this.idCita = idCita;
        this.monto = monto;
        this.idMetodoDePago = idMetodoDePago;
    }
    
    
}
