/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Image;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import model.dao.UsuarioDAO;
import model.usuario.Usuario;
import view.Usuarios;

/**
 *
 * @author Keren
 */
public class UsuarioController {

    private final Usuarios vista;
    private final UsuarioDAO dao;
    private Usuario usuarioSeleccionado = null;

    public UsuarioController(Usuarios vista, UsuarioDAO dao) {
        this.vista = vista;
        this.dao = dao;

        initListeners();
        cargarUsuarios();
    }

    private void initListeners() {
        // Evento cuando el botón Crear/Actualizar es presionado
        vista.getBtnCrearActualizar().addActionListener(e -> crearOActualizar());

        // Evento cuando se selecciona un usuario de la lista
        vista.getListUsuarios().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                usuarioSeleccionado = vista.getListUsuarios().getSelectedValue();
                if (usuarioSeleccionado != null) {
                    cargarDatosEnFormulario(usuarioSeleccionado);
                    // Cambiar el texto del botón a "Actualizar"
                    vista.getBtnCrearActualizar().setText("Actualizar");
                } else {
                    // Si no hay selección, restaurar el botón a "Crear"
                    vista.getBtnCrearActualizar().setText("Crear");
                }
            }
        });
    }

    private void cargarUsuarios() {
        try {
            List<Usuario> usuarios = dao.listarUsuarios();
            DefaultListModel<Usuario> model = new DefaultListModel<>();
            for (Usuario u : usuarios) {
                model.addElement(u);
            }
            vista.getListUsuarios().setModel(model);
        } catch (SQLException e) {
            mostrarError("Error al cargar usuarios: " + e.getMessage());
        }
    }

    private void crearOActualizar() {
        if (!vista.validarCampos()) {
            return; // Validar los campos
        }
        Usuario u = vista.obtenerDatos(); // Obtener los datos del formulario

        try {
            // Verificar si es un nuevo usuario o una actualización
            if (usuarioSeleccionado == null) {
                dao.agregarUsuario(u);  // Crear nuevo usuario
                mostrarMensaje("Usuario creado exitosamente.");
            } else {
                u.setId(usuarioSeleccionado.getId()); // Mantener el ID para actualización
                dao.actualizarUsuario(u);  // Actualizar usuario existente
                mostrarMensaje("Usuario actualizado correctamente.");
            }

            // Limpiar campos y restablecer el botón a "Crear"
            vista.limpiarCampos();
            vista.getBtnCrearActualizar().setText("Crear");  // Cambiar el texto del botón a "Crear"
            usuarioSeleccionado = null; // Resetear la variable de usuario seleccionado

            // Recargar la lista de usuarios
            cargarUsuarios();
        } catch (SQLException e) {
            mostrarError("Error al guardar usuario: " + e.getMessage());
        }
    }

    private void cargarDatosEnFormulario(Usuario u) {
        vista.getTxtNombre().setText(u.getNombre());
        vista.getTxtApellido().setText(u.getApellido());
        vista.getTxtUsuario().setText(u.getUsuario());
        vista.getTxtContraseña().setText(u.getContrasena());
        vista.getTxtEmail().setText(u.getEmail());
        vista.getTxtTelefono().setText(u.getTelefono());
        vista.getCbxRol().setSelectedItem(u.getRol());
        vista.getChbActivado().setSelected(u.isActivo());

        // Cargar imagen en el JLabel si existe
        if (u.getImagen() != null && u.getImagen().length > 0) {
            ImageIcon icon = new ImageIcon(u.getImagen());
            this.vista.getAvatar().setIcon(icon);
//            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); // ajusta tamaño
            
//            vista.getJlbImagenAvatar().setIcon(new ImageIcon(img));
        } else {
            vista.getAvatar().setIcon(new ImageIcon("/view/component/menu/icons/avatar/p2.png")); // o null
        }

    }

    private void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(vista, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(vista, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
