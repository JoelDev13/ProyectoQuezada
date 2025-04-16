/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author la
 */
public class HistorialPago {
    private String paciente;
    private String doctor;
    private String fecha;
    private double total;
    private String servicioRealizado;
    private String metodoPago;

    // Constructor
    public HistorialPago(String paciente, String doctor, String fecha, double total, String servicioRealizado, String metodoPago) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.total = total;
        this.servicioRealizado = servicioRealizado;
        this.metodoPago = metodoPago;
    }

    // Getters y setters
    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getServicioRealizado() {
        return servicioRealizado;
    }

    public void setServicioRealizado(String servicioRealizado) {
        this.servicioRealizado = servicioRealizado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
