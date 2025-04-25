/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import DBconexion.ConexionDB;
import model.HistorialPago;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HistorialPagoModel;

/**
 *
 * @author la
 */
public class HistorialPagosDAO {
    
    /**
     * Registra un registro de un pago al sistema.
     * @param historial objeto <code>HistorialPagoModel</code> con las informacion es a resgistrar
     * @throws SQLException con mensaje de la db
     */

    public void registrarPago(HistorialPagoModel historial) throws SQLException {
        String sql = "{CALL sp_registrar_pago(?,?,?)}";

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql);) {
            cs.setInt(1, historial.getIdCita());
            cs.setDouble(2, historial.getMonto());
            cs.setInt(sql, historial.getIdMetodoDePago());
            cs.execute();

        }
    }

    
    /**
     * Filtra los registros con el parametro entregado
     * @param filtro descripcion con la que filtrar
     * @return <code>List&lt;HistorialPago&gt;</code> con los registros que cumplan el filtro.
     */
    public List<HistorialPago> buscarHistoricoPagos(String filtro) {
        List<HistorialPago> pagos = new ArrayList<>();
        String sql = "{CALL BuscarHistoricoPagos(?)}";  // Llamada al STORED PROCEDURE: Mostrar Historial De pagos

        try (Connection conn = ConexionDB.obtenerConexion(); // Usamos la conexión centralizada
                 CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, "%" + filtro.toLowerCase() + "%");  // Filtro insensible a mayúsculas y minúsculas
            ResultSet rs = stmt.executeQuery();  // Ejecuta la consulta

            while (rs.next()) {
                String paciente = rs.getString("paciente");
                String doctor = rs.getString("doctor");
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");
                String servicioRealizado = rs.getString("servicio_realizado");
                String metodoPago = rs.getString("metodo_pago");

                pagos.add(new HistorialPago(paciente, doctor, fecha, total, servicioRealizado, metodoPago));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener historial de pagos: " + e.getMessage());
        }
        return pagos;
    }
}
