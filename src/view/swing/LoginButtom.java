
package view.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author luis-
 */
public class LoginButtom extends JButton{

    private boolean mousePress;

    public LoginButtom() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3,0,3,0));
        setSize(55,55);
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePress = true;
                System.out.print("Presionado");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePress = false;
            }
        });
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Anthilashing para que nuestro futuro circulo sea suave.
        int width = this.getWidth(); // obtenemos el ancho y alto de este componnente.
        int height = this.getHeight();
        int size = Math.min(width, height); // elegimos el valor menor entre los dos. este sera el diametro del circulo.
        int x = (width - size) / 2; // esta operacion es para centrar el circulo vertical y horinzontalmente.
        int y = (height - size) / 2;

        if (mousePress) {
            Color temp = Color.decode("#4b7f52");
            temp.brighter();
            g2.setColor(temp);
        } else {
            g2.setColor(Color.decode("#61a46c"));
            
        }

        g2.fill(new Ellipse2D.Double(x, y, size, size));
        g2.dispose();

        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

    }

    
}
