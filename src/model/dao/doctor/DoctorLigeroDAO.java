/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.doctor;

import java.util.List;
import model.doctor.DoctorLigeroDTO;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import DBconexion.ConexionDB;
import java.time.LocalTime;
import java.util.ArrayList;
import model.horario.Horario;

/**
 * este dao se encargara de las consultas de los Dialog que filtran Doctores.
 * Este implementa metodos y store procedures propios para ellos.
 *
 * @author luis-
 * @see DoctorLigeroDTO
 */
public class DoctorLigeroDAO {
    
    /**
     * Obtiene todos los horarios de una dia (Lunes,martes...) especifico de un doctor
     * @param idDoc ID del doctor
     * @param dia Un dia especifico, puede ser: (LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO)
     * @return List&lt;Horario&gt; con todos los horarios de un dia especifico de un doctor especifico
     * @throws SQLException con mensaje de la db
     */
    public List<Horario> ObtenerHorariosDeUnDia(int idDoc, String dia) throws SQLException{
        String sql = "{CALL sp_horarios_dia(?,?)}";
        List<Horario> horarios = new ArrayList<>(); 
         try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql);) {
             cs.setInt(1, idDoc);
             cs.setString(2, dia);
             ResultSet rs = cs.executeQuery();
             
             while (rs.next()) {
                 Horario h = new Horario();
                 h.setId(rs.getInt("id"));
                 h.setDia(rs.getString("dia"));
                 h.setHoraInicio(rs.getObject("inicio", LocalTime.class));
                 h.setHoraFin(rs.getObject("fin", LocalTime.class));
                 horarios.add(h);
             }
         }
         return horarios;
    }
    
    /**
     * Retorna todos los dias habiles (los dias en los que el doctor posee algun horario)
     * @param idDoc ID del doctor
     * @return List&lt;String&gt; con todos los dias habiles del Doctor. Pueden ser (LUNES, MARTES, ... DOMINGO)
     * @throws SQLException con mensaje de la db
     */
    public List<String> diasHabiles(int idDoc) throws SQLException {
        String sql = "{CALL sp_dias_habiles(?)}";
        List<String> diasHabiles = new ArrayList<>();
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql);) {
            cs.setInt(1, idDoc);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                diasHabiles.add(rs.getString("dia"));
            }
            return diasHabiles;
        }
    }
    
    /**
     * Filtra a los doctores que se parezcan a las descripciones entregadas
     * @param doctor Objeto <code>DoctorLigeroDTO</code> con las descripciones a usar
     * @return List&lt;DoctorLigeroDTO&gt;
     * @throws SQLException con mensaje de la db
     */
    
    public List<DoctorLigeroDTO> filtrarDoctores(DoctorLigeroDTO doctor) throws SQLException {
        String sql = "{CALL sp_filtrar_doctoresDTO(?,?,?)}";
        List<DoctorLigeroDTO> doctores = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, doctor.getNombre());
            cs.setString(2, doctor.getApellido());
            cs.setString(3, doctor.getEspecialidad());

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                DoctorLigeroDTO d = new DoctorLigeroDTO();
                d.setId(rs.getInt("ID"));
                d.setNombre(rs.getString("nombre"));
                d.setApellido(rs.getString("apellido"));
                d.setEspecialidad(rs.getString("especialidad"));
                doctores.add(d);
            }
        }
        return doctores;
    }
}
