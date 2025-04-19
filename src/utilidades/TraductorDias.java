/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 * En esta clase se encapsula la funcionalidad de traducir un string
 * que represente un dia en la db ('LUNES', 'MARTES'... 'DOMINGO')
 * a un valor del Enum de DayOfTheWeek. Esto es para simplificar
 * las operaciones que toman fechas de DatePicker para consultar o
 * enviar informacion a la db
 * @author luis-
 */
import java.time.DayOfWeek;
import java.util.*;

public class TraductorDias {

    // Mapa de string a DayOfWeek
    private static final Map<String, DayOfWeek> textoADia;
    
    // Mapa DayOfWeek a string
    private static final Map<DayOfWeek, String> diaATexto;

    static {
        Map<String, DayOfWeek> forward = new HashMap<>();
        forward.put("LUNES", DayOfWeek.MONDAY);
        forward.put("MARTES", DayOfWeek.TUESDAY);
        forward.put("MIERCOLES", DayOfWeek.WEDNESDAY);
        forward.put("JUEVES", DayOfWeek.THURSDAY);
        forward.put("VIERNES", DayOfWeek.FRIDAY);
        forward.put("SABADO", DayOfWeek.SATURDAY);
        forward.put("DOMINGO", DayOfWeek.SUNDAY);

        textoADia = Collections.unmodifiableMap(forward);
        
        // creamos otro map con los valores invertidos (Enum a String)
        Map<DayOfWeek, String> reverse = new EnumMap<>(DayOfWeek.class);
        for (Map.Entry<String, DayOfWeek> entry : forward.entrySet()) {
            reverse.put(entry.getValue(), entry.getKey());
        }

        diaATexto = Collections.unmodifiableMap(reverse);
    }

    // Día en String a DayOfWeek
    public static DayOfWeek aDayOfWeek(String diaEnEspañol) {
        return textoADia.get(diaEnEspañol);
    }

    // DayOfWeek a String
    public static String aString(DayOfWeek dayOfWeek) {
        return diaATexto.get(dayOfWeek);
    }
}