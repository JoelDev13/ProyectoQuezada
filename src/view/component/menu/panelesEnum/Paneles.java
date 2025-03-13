/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package view.component.menu.panelesEnum;

/**
 *
 * @author luis-
 * Este enum recopila los identificadores unicos de todos
 * los paneles existentes
 */

public enum Paneles {
    GESTOR_DE_PACIENTES,
    AGENDAR_CITAS,
    GESTOR_DE_CITAS,
    GESTOR_DE_PACIENTES_DOC, // No estoy seguro si sera un panel aparte o el mismo.
    AGENDA_DOC,
    GESTOR_DE_USUARIOS,
    GESTOR_DE_DOCTORES,
    GESTOR_DE_TURNOS,
    GESTOR_DE_SERVICIOS,
    PAGOS,
    LOG_OFF // Creamos uno para el log out para no complicarnos.
}
