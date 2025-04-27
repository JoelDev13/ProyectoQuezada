/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.especialidad;

import java.util.ArrayList;
import java.util.List;
import model.especialidad.Especialidad;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import DBconexion.ConexionDB;

/**
 * Clase que gestiona y manipula los cambios en la base de datos asociados a las especialidades
 *
 * @author luis-
 */
public class EspecialidadDao {

    /**
     * Trae todas las especialidades que tiene un medico
     *
     * @param idDoc id del medico
     * @return <code>List&lt;Especialidad&gt;</code> con todas las especialidades del medico
     * @throws SQLException con mensaje de la db
     */
    public List<Especialidad> listarEspecialidadesDeUnMedico(int idDoc) throws SQLException {
        String sql = "{CALL sp_listar_especialidades_medico_especifico(?)}";
        List<Especialidad> especialidades = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idDoc);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Especialidad e = new Especialidad();
                    e.setId(rs.getInt("id"));
                    e.setDescripcion(rs.getString("descripcion"));
                    especialidades.add(e);
                }

                return especialidades;
            }
        }
    }

    /**
     * Trae todas las especialidades registradas
     *
     * @return <code>List&lt;Especialidad&gt;</code> con todas las especialidades registradas
     * @throws SQLException con un mesaje de la db
     */
    public List<Especialidad> listarEspecialidades() throws SQLException {
        String sql = "{CALL filtrar_especialidades()}";
        List<Especialidad> especialidades = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {

            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Especialidad e = new Especialidad();
                    e.setId(rs.getInt("id"));
                    e.setDescripcion(rs.getString("descripcion"));
                    especialidades.add(e);
                }
            }
            return especialidades;
        }
    }

    public void agregarEspecialidad(Especialidad esp) throws SQLException {
        String sql = "{CALL sp_crear_especialidad(?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, esp.getDescripcion());
            cs.execute();
            cs.close();
        }
    }

    public void eliminarEspecialidad(Integer id) throws SQLException {
        String sql = "{CALL sp_eliminar_especialidad(?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
            cs.close();
        }
    }

    /**
     * Elimina TODOS los servicios asociados a esta especialidad
     *
     * @param id
     */
    public void eliminarServiciosAsociadosDeEstaEspecialidad(int id) throws SQLException {
        String sql = "{CALL sp_eliminar_servicios_asociados_de_especialidad(?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.execute();
            cs.close();
        }
    }

    public void asociarServiciosAespecialidad(int idEspecialidad, int idServicio) throws SQLException{
        String sql = "{CALL sp_asociar_servicio_a_especialidad(?, ?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idEspecialidad);
            cs.setInt(2, idServicio);
            cs.execute();
            cs.close();
        }
    }
}
