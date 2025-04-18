/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component.table;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * tabla con diseño. Todos los elementos estan alineados al izquierda
 * @author luis-
 */
public class CustomTable extends JTable {
    public CustomTable () {
        super();
        this.customizar();
    }
    
    private void customizar() {
        this.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height: 30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                //+ "separatorColor:$TableHeader.background;" // linea separadora de los headers
                + "font:bold;");

        this.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;" // muestra la separacion de las filas en un select multiple
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;"); // cuando se cambia el hoverBackground las letras se vuelven blancas. lo arreglamos.



        this.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(this));
    }
    
    public void customizarScrollBar(JScrollPane scroll) {
                scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets: 3,3,3,3;"
                + "thumbInsets: 3,3,3,3;"
                + "background:$Table.background;");
    }
    
    public void ponerCellRendererEnColumna( TableCellRenderer renderer, int columna) {
        this.getColumnModel().getColumn(columna).setCellRenderer(renderer);
    }
    
    public void ponerCellEditorEnColumna(TableCellEditor editor, int columna) {
        this.getColumnModel().getColumn(columna).setCellEditor(editor);
    }
}
