/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.citas;

import DBconexion.ConexionDB;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.citas.Citas;
import model.citas.CitasDTO;

/**
 *
 * @author luis-
 */
public class CitasDao {
    
    // Solo Dios sabe el trabajo que tomo esto.
    /**
     * Metodo usado par filtrar citas que segun las descripciones dadas. Los parametros enviados que sean null no seran considerados ala hora de filtrar en la base de datos. Debido a que estamos filtrando en base si nos entregaron un filtro o no (null) tenemos que usar Integer en vez del dato primitivo int y asegurarnos que el dato que nos entregaron es null o no. De ahi tantos Checks
     *
     * @param filtros
     * @return
     * @throws SQLException
     */
    public List<CitasDTO> filtrarCitas(Citas filtros) throws SQLException {
        String sql = "{CALL sp_filtrar_citas(?,?,?,?,?,?,?)}";
        List<CitasDTO> citas = new ArrayList<>();
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {

            PonerIntNulo(cs, 1, filtros.getIdPaciente());
            PonerIntNulo(cs, 2, filtros.getIdDoctor());
            PonerIntNulo(cs, 3, filtros.getIdServicio());
            PonerIntNulo(cs, 4, filtros.getIdEspecialidad());

            cs.setString(5, filtros.getEstado()); // Estado es un String, setString maneja nulls por si solo.

            PonerFechasNulo(cs, 6, filtros.getFechaInicioFiltro());
            PonerFechasNulo(cs, 7, filtros.getFechaFinFiltro());

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                CitasDTO citasDto = new CitasDTO();
                citasDto.setId(rs.getInt("id_cita"));
                citasDto.setNombrePaciente(rs.getString("paciente"));
                citasDto.setCedulaPaciente(rs.getString("cedula"));
                citasDto.setNombreDoctor(rs.getString("doctor"));
                citasDto.setEspecialidad(rs.getString("especialidad"));
                citasDto.setServicio(rs.getString("servicio"));
                citasDto.setFecha(rs.getDate("fecha").toLocalDate());
                citasDto.setEstado(rs.getString("estado"));
                citasDto.setHoraInicio(rs.getObject("inicio", LocalTime.class));
                citasDto.setHoraFin(rs.getObject("fin", LocalTime.class));
                citas.add(citasDto);

            }

        }
        return citas;
    }

    private void PonerIntNulo(CallableStatement cs, int indiceSQL, Integer valor) throws SQLException {
        if (valor != null) {
            cs.setInt(indiceSQL, valor);
        } else {
            cs.setNull(indiceSQL, Types.INTEGER);
        }

    }

    private void PonerFechasNulo(CallableStatement cs, int indiceSQL, LocalDate fecha) throws SQLException {
        if (fecha != null) {
            cs.setDate(indiceSQL, Date.valueOf(fecha));
        } else {
            cs.setNull(indiceSQL, Types.DATE);
        }

    }
}
