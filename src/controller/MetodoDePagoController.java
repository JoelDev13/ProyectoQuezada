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
    
    // Método para validar la descripción
    public boolean validarDescripcion(String descripcion) {
        // Verificar que la descripción no esté vacía
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("La descripción no puede estar vacía.");
            return false;
        }

        // Verificar que la descripción solo contenga letras y espacios
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(descripcion);
        if (!matcher.matches()) {
            System.out.println("La descripción solo puede contener letras y espacios.");
            return false;
        }

        // Verifica que la descripción no esté repetida
        if (dao.obtenerPorDescripcion(descripcion) != null) { 
            System.out.println("La descripción ya está en uso.");
            return false;
        }

        return true;
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
