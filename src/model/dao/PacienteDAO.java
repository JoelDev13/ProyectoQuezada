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

/**
 *
 * @author luis-
 */
public class PacienteDAO {
    
    
    public List listarPacientes() throws SQLException{
        String sql = "{CALL sp_listar_pacientes()}";
        List<Paciente> pacientes = new ArrayList();

        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
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
    
    
    public List FiltrarPacientes(Paciente p) throws SQLException {

        String sql = "{CALL sp_filtrar_pacientes(?,?,?,?,?,?,?,?,?)}";
        List<Paciente> pacientes = new ArrayList();

        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
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

    
    
    public void agregarPaciente(Paciente p) throws SQLException {
        String sql = "{CALL sp_crear_pacientes(?,?,?,?,?,?,?,?,?)}";
        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
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
    
    
    public void actualizarPaciente(Paciente p) throws SQLException {
        String sql = "{CALL sp_actualizar_pacientes(?,?,?,?,?,?,?,?,?)}";
        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
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
    
    
    public void eliminarPaciente (String cedula) throws SQLException {
        String sql = "{CALL sp_eliminar_paciente(?)}";
        try (Connection conn = ConexionDB.obtenerConeccion(); CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, cedula);
            cs.execute();
        }
    }
}

