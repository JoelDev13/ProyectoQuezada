package controller;

import model.HistorialPago;
import model.dao.HistorialPagosDAO;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Controllador de la vista historial de pagos
 * @author la
 */
public class HistorialPagosController {
    private HistorialPagosDAO dao = new HistorialPagosDAO();

    /**
     * Filtra los registros que cumplan con el filtro
     * @param filtro con el que buscar registros.
     * @return una lista de objetos de <code>HistorialPago</code>
     */
    public List<HistorialPago> buscar(String filtro) {
        if (filtro == null) filtro = "";
        String filtroSQL = "%" + filtro.toLowerCase() + "%";
        return dao.buscarHistoricoPagos(filtroSQL);
    }
}

    

