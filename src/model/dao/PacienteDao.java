/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import DBconexion.ConexionDB;
import java.util.List;
import java.util.ArrayList;
import model.paciente.Paciente;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import model.paciente.PacienteDTO;

/**
 * DAO para manejar y manipular la informacion de los pacientes.
 * Usado en la vista del panel Paciente.java
 * 
 * @author luis-
 * @see #Paciente 
 * @see #PacienteDao() 
 */
public class PacienteDao {
    
    /**
     * Trae todos los pacientes registrados
     * @return <code>List&lt;Paciente&gt</code> con todos los pacientes
     * @throws SQLException con un mensaje desde la db
     */
    public List listarPacientes() throws SQLException{
        String sql = "{CALL sp_listar_pacientes()}";
        List<Paciente> pacientes = new ArrayList();

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            ResultSet rs = cs.executeQuery(); 
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setCedula(rs.getString("cedula"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setSeguro(rs.getString("seguro"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                pacientes.add(paciente);

            }

        } 
        return pacientes;
    }
    
    /**
     * 
     * Trae todos los pacientes que tengan parecido a los valores entregados por el usuario.
     *
     * @return <code>List&lt;Paciente&gt</code> con todos los pacientes que cumplan las descripciones entregadas
     * @param p objeto <code>Paciente</code> que contiene todas las descripciones
     * @throws SQLException con un mensaje desde la db
     */
    public List FiltrarPacientes(Paciente p) throws SQLException {

        String sql = "{CALL sp_filtrar_pacientes(?,?,?,?,?,?,?,?,?)}";
        List<Paciente> pacientes = new ArrayList();

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, p.getNombre());
            cs.setString(2, p.getApellido());
            cs.setString(3, p.getCedula());
            cs.setString(4, p.getSexo());
            cs.setString(5, p.getEmail());
            cs.setString(6, p.getTelefono());
            cs.setString(7, p.getDireccion());
            cs.setString(8, p.getSeguro());
            cs.setDate(9, (p.getFechaNacimiento() == null) ? null : Date.valueOf(p.getFechaNacimiento())); // tengo que asegurarme que sea Null o no null Aqui.

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setCedula(rs.getString("cedula"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setSeguro(rs.getString("seguro"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                pacientes.add(paciente);
            }

        }
        return pacientes;
    }

    
    /**
     * 
     * Trae todos los pacientes que tengan parecido a los valores entregados por el usuario.
     * Este metodo es para Pacientes DTO, una simplificacion de Pacientes
     *
     * @return <code>List&lt;PacienteDTO&gt</code> con todos los pacientes que cumplan las descripciones entregadas
     * @param p objeto <code>PacienteDTO</code> que contiene todas las descripciones
     * @throws SQLException con un mensaje desde la db
     */
    public List FiltrarPacientesDTO(PacienteDTO p) throws SQLException {

        String sql = "{CALL sp_filtrar_pacientesDTO(?,?,?)}";
        List<PacienteDTO> pacientes = new ArrayList();

        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, p.getNombre());
            cs.setString(2, p.getApellido());
            cs.setString(3, p.getCedula());

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                PacienteDTO paciente = new PacienteDTO();
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setCedula(rs.getString("cedula"));
                pacientes.add(paciente);
            }
        }
        return pacientes;
    }
    
    
    /**
     * Registra un paciente nuevo en la DB.
     * @param p objeto <code>Paciente</code> con todos los datos de un Paciente
     * @throws SQLException con un mensaje de la db
     */
    public void agregarPaciente(Paciente p) throws SQLException {
        String sql = "{CALL sp_crear_pacientes(?,?,?,?,?,?,?,?,?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, p.getNombre());
            cs.setString(2, p.getApellido());
            cs.setString(3, p.getCedula());
            cs.setString(4, p.getSexo());
            cs.setString(5, p.getEmail());
            cs.setString(6, p.getTelefono());
            cs.setString(7, p.getDireccion());
            cs.setString(8, p.getSeguro());
            cs.setDate(9, Date.valueOf(p.getFechaNacimiento())); // Convertir de LocalDate a Date, para que funcione el insert.
            cs.execute();
        }
    }
    
    /**
     * Actualiza los datos de un paciente en la DB. Se usa la cedula
     * de la persona como ID
     * @param p objeto <code>Paciente</code> con la nueva informacion de la persona
     * @throws SQLException con un mensaje de la db
     */
    public void actualizarPaciente(Paciente p) throws SQLException {
        String sql = "{CALL sp_actualizar_pacientes(?,?,?,?,?,?,?,?,?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, p.getNombre());
            cs.setString(2, p.getApellido());
            cs.setString(3, p.getCedula());
            cs.setString(4, p.getSexo());
            cs.setString(5, p.getEmail());
            cs.setString(6, p.getTelefono());
            cs.setString(7, p.getDireccion());
            cs.setString(8, p.getSeguro());
            cs.setDate(9, Date.valueOf(p.getFechaNacimiento())); // Convertir de LocalDate a Date, para que funcione el insert.
            cs.execute();
        }
    }
    
    /**
     * Elimina la informacion de un paciente. 
     * @param cedula ID que se usara para eliminar la informacion
     * @throws SQLException con mensaje desde la db
     */
    public void eliminarPaciente (String cedula) throws SQLException {
        String sql = "{CALL sp_eliminar_paciente(?)}";
        try (Connection conn = ConexionDB.obtenerConexion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, cedula);
            cs.execute();
        }
    }
}

