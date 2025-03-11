
package view.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private ActionListener actionListener; // Para capturar el evento del boton

    public LoginButtom() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 0, 3, 0));
        setSize(55, 55);
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePress = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePress = false;
                repaint();
                if (actionListener != null) {
                    actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });
    }

    public void addActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = this.getWidth();
        int height = this.getHeight();
        int size = Math.min(width, height);
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        if (mousePress) {
            g2.setColor(Color.decode("#4b7f52").brighter());
        } else {
            g2.setColor(Color.decode("#61a46c"));
        }

        g2.fill(new Ellipse2D.Double(x, y, size, size));
        g2.dispose();

        super.paintComponent(g);
    }
    
}
