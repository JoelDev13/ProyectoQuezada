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
import javax.lang.model.util.Types;
import model.citas.Citas;
import model.citas.CitasDTO;


/**
 * Clase que se encarga de manipular los datos de una cita
 *
 * @author luis-
 */
public class CitasDao {

    /**
     * Actualiza la fecha y estado de una cita.
     *
     * @param actualizacion objeto Cita con el ID de cita, fecha nueva y estado nuevo.
     * @throws SQLException con un mensaje de la base de datos
     */
    public void actualizarCita(Citas actualizacion) throws SQLException {
        String sql = "{CALL sp_actualizar_cita(?,?,?,?,?,?,?,?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, actualizacion.getId());
            cs.setInt(2, actualizacion.getIdPaciente());
            cs.setInt(3, actualizacion.getIdDoctor());
            cs.setInt(4, actualizacion.getIdHorarioDoctor());
            cs.setInt(5, actualizacion.getIdServicio());
            cs.setInt(6, actualizacion.getIdEspecialidad());
            cs.setDate(7, java.sql.Date.valueOf(actualizacion.getFecha()));
            cs.setString(8, actualizacion.getEstado());
            cs.execute();
        }
    }

    /**
     * Actualiza la fecha y estado de una cita.
     *
     * @param cita objeto Cita con el ID de cita, fecha nueva y estado nuevo.
     * @throws SQLException con un mensaje de la base de datos
     */
    public void AgendarUnaCita(Citas cita) throws SQLException {
        String sql = "{CALL sp_agendar_cita(?,?,?,?,?,?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, cita.getIdPaciente());
            cs.setInt(2, cita.getIdDoctor());
            cs.setInt(3, cita.getIdHorarioDoctor());
            cs.setInt(4, cita.getIdServicio());
            cs.setInt(5, cita.getIdEspecialidad());
            cs.setDate(6, java.sql.Date.valueOf(cita.getFecha()));
            cs.execute();
        }
    }

    // Solo Dios sabe el trabajo que tomo esto.
    /**
     * Metodo usado par filtrar citas que segun las descripciones dadas. Los parametros enviados que sean null no seran considerados ala hora de filtrar en la base de datos. Debido a que trabajamos con nulls para saber si nos enviaron o no un filtro, usamos la clase Integer
     *
     * @param filtros obj <code>Citas</code> con los filtros
     * @return <code>List&lt;CitasDTO&gt; con los registros que cumplan los filtros
     * @throws SQLException con mesaje de la db
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

            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    CitasDTO citasDto = new CitasDTO();
                    citasDto.setId(rs.getInt("id_cita"));
                    citasDto.setIdPaciente(rs.getInt("id_paciente"));
                    citasDto.setIdDoc(rs.getInt("id_doctor"));
                    citasDto.setIdServicio(rs.getInt("id_servicio"));
                    citasDto.setIdEspecialidad(rs.getInt("id_especialidad"));
                    citasDto.setIdHorario(rs.getInt("id_horario"));

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

        }
        return citas;
    }

    /**
     * Este metodo se encarga de poner Null explicitamente en los campos int del Callabe Statemnt para evitar errores
     *
     * @param cs Callable Statement
     * @param indiceSQL Indice que se va a sustituir
     * @param valor valor a sustituir
     * @throws SQLException con mensaje de la db
     */
    private void PonerIntNulo(CallableStatement cs, int indiceSQL, Integer valor) throws SQLException {
        if (valor != null) {
            cs.setInt(indiceSQL, valor);
        } else {
            cs.setNull(indiceSQL, java.sql.Types.INTEGER);
        }

    }

    /**
     * Este metodo se encarga de poner Null explicitamente en los campos Date del Callabe Statemnt para evitar errores
     *
     * @param cs Callable Statement
     * @param indiceSQL Indice que se va a sustituir
     * @param valor valor a sustituir
     * @throws SQLException con mensaje de la db
     */
    private void PonerFechasNulo(CallableStatement cs, int indiceSQL, LocalDate fecha) throws SQLException {
        if (fecha != null) {
            cs.setDate(indiceSQL, java.sql.Date.valueOf(fecha));
        } else {
            cs.setNull(indiceSQL, java.sql.Types.DATE);
        }

    }
}
