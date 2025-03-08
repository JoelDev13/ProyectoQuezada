package view.component;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author luis-
 * 
 * Esta clase tiene 3 propiedades que puedes cambiar en el 
 * explorador de propiedades de netbeans. ForeGround, borderSize y Icon.
 * Si cambias estas propiedades el componente modificara su aspecto visual.
 * Compruebalo tu mismo, cambia alguna propiedad
 */
public class ImageAvatar extends JComponent {

    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;

    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
        repaint();
    }

    /**
     * @return the borderSize
     */
    public int getBorderSize() {
        return borderSize;
    }

    /**
     * @param borderSize the borderSize to set
     */
    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }
    
    

    private Icon icon;
    private int borderSize;

    /**
     * Esta sobreEscritura pinta de manera circular la foto de perfil
     * del usuario. También pinta un borde del color de #foreGround alrededor de la foto 
     * Si el valor de borderSize es diferente de cero.
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {
        /*
            La idea detras de este bloque de codigo es el de crear dos circulos, uno
            mas grande que el otro, para sobreponer el mas pequeño encima del mas grande.
            El borde seria el circulo mas grande, y la foto del perfil del usuario seria el mas pequeño
        */
        if (icon != null) {
            System.out.println("Me llamaron, Sea Directamente o Indirectamente");
            int width = getWidth();
            int height = getHeight();
            int diameter = Math.min(width, height); // El diametro del ciculo sera la Dmension mas pequeña.

            // Centramos el circulo
            int x = (width - diameter) / 2; 
            int y = (height - diameter) / 2;
            
            // En caso de que este perfil tenga borde, se lo quitamos al diametro
            int border = borderSize * 2;
            diameter -= border;
            
            // Creamos una imagen (en blanco) con las dimensiones del diametro
            // Y en ella pintamos el perfil del usuario
            // Ten en cuenta que este proceso ocurre solamente en el espacio del bufferedImage.
            Dimension size = getAutoSize(icon, diameter);
            BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2_img = img.createGraphics(); // Creamos un objeto graphics capaz de pintar en esta imagen
            g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // para un circulo fluido
            g2_img.fillOval(0, 0, diameter, diameter); // pintamos el circulo donde estara la imagen

            Composite composite = g2_img.getComposite(); // Se obtiene el Composite de la imagen
            g2_img.setComposite(AlphaComposite.SrcIn); // al usar esta opcion, se pintaran los pixeles previamente pintados
            g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2_img.drawImage(toImage(icon), 0, 0, size.width, size.height, null);
            g2_img.setComposite(composite);
            g2_img.dispose();
            
            // Pintamos el borde del perfil (esto lo pintamos en el espacio del componente
            // (es decir, no el el BufferedImage anterior)
            Graphics2D g2 = (Graphics2D) g;
            g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (borderSize > 0) {
                diameter += border;
                g2.setColor(getForeground());
                g2.fillOval(x, y, diameter, diameter);
            }
            g2.drawImage(img, x + borderSize, y + borderSize, null);
        }

        super.paintComponent(g);
    }

    /**
     * Reescala la imagen en base a la relacion (el cociente) que hay entre las dimensiones 
     * de la imagen entregada y las dimensiones del componente que contiene dicha imagen.
     *
     * @param image Imagen original
     * @param size Tamaño a redimensionar
     * @return
     */
    private Dimension getAutoSize(Icon image, int size) {
        int w = size;
        int h = size;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();

        double xScale = (double) w / iw;
        double yScale = (double) h / iw;
        double scale = Math.max(xScale, yScale);

        int width = (int) (scale * iw);
        int height = (int) (scale * ih);

        return new Dimension(width, height);

    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }

}
