/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component.table;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;


/**
 * Este renderizador alinea las columnas de la tabla.
 * Este renderizador modifica los Header las celdas comunes dela tabla
 * entregada como parametro. El constructor se encarga de modificar las celdas
 * comunes, mientras que la sobreescritura al metodo @getTableComponent se encarga
 * de las celdas que son headers
 * 
 * @author luis-
 */
public class TableHeaderAlignment implements TableCellRenderer {
    //Guardamos una referencia al redendirazor del header de la tabla
    private final TableCellRenderer oldHeaderRenderer;
    // Guardamos una referencia al rederizador de todas las otras celdas de la tabla
    private final TableCellRenderer oldCellRenderer;
 

    /**
     * Este constructor se encarga de settear el alineado de todas
     * las celdas de la tabla que recibe.
     * @param table referencia a la tabla que se va alinear
     */
    public TableHeaderAlignment(JTable table) {
        // El metodo getDefaultRenderer recupera el renderizador particular de un tipo de objeto.
        // al pasarle como parametro la clase Object obtenemos el renderizador de este tipo de objetos, obteniendo asi
        // el renderizador general de la tabla (todos derivan de object).

        this.oldHeaderRenderer = table.getTableHeader().getDefaultRenderer();        
        this.oldCellRenderer = table.getDefaultRenderer(Object.class);

        
        // Con la referencia de al renderizador de todas las celdas de la tabla
        // extraemos el componente de dicha celda (que es un Jlabel) para alinear su texto.
        // ESTE CODIGO SOLAMENTE AFECTA LAS CELDAS QUE NO SON HEADERS.
        table.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
                JLabel label = (JLabel) oldCellRenderer.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
                label.setHorizontalAlignment(getAlignment(column));
                return label;
            }
        });
    }

    
    // La idea de modificar el renderizador de las celdas es la misma aca.
    // Pero este codigo solamente afecta a los headers.
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
        JLabel label = (JLabel) oldHeaderRenderer.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
        label.setHorizontalAlignment(getAlignment(column));
        return label;
    }

    protected int getAlignment(int column) {
        if (column == 0) {
            return SwingConstants.CENTER;
        }
        return SwingConstants.LEADING;
    }
}