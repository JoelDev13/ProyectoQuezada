/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.dao.MetodoDePagoDao;
import model.MetodoDePagoModel;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author la
 */
public class MetodoDePagoController {
    private MetodoDePagoDao dao;

    public MetodoDePagoController() {
        dao = new MetodoDePagoDao();
    }
    
    public List<MetodoDePagoModel> obtenerMetodos() {
        return dao.listarMetodos();
    }
    
      // Método para validar el ID
    public boolean validarId(int id) {
        // Verifica que el ID sea un número positivo y mayor que cero
        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return false;
        }
        
        // Verifica que el ID no esté repetido
        MetodoDePagoModel metodoExistente = dao.obtenerPorId(id);
        if (metodoExistente != null) {
            System.out.println("El ID ya está en uso.");
            return false;
        }

        return true;
    }
    
public boolean validarDescripcion(String descripcion, int idActual) {
    if (descripcion == null || descripcion.trim().isEmpty()) {
        System.out.println("La descripción no puede estar vacía.");
        return false;
    }
    
    // Verifica formato
    Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
    Matcher matcher = pattern.matcher(descripcion);
    if (!matcher.matches()) {
        System.out.println("La descripción solo puede contener letras y espacios.");
        return false;
    }
    
    // Verifica duplicados EXCEPTO si es el mismo registro
    MetodoDePagoModel existente = dao.obtenerPorDescripcion(descripcion);
    if (existente != null && existente.getId() != idActual) {
        System.out.println("La descripción ya está en uso por otro método de pago.");
        return false;
    }
    
    return true;
}

// Versión sobrecargada para compatibilidad
public boolean validarDescripcion(String descripcion) {
    return validarDescripcion(descripcion, 0);
}


   
    public boolean guardarMetodo(String descripcion) {
        return dao.insertarMetodo(descripcion);
    }

    public boolean actualizarMetodo(int id, String descripcion) {
        return dao.actualizarMetodo(id, descripcion);
    }

    public boolean eliminarMetodo(int id) {
        return dao.eliminarMetodo(id);
    }
}
